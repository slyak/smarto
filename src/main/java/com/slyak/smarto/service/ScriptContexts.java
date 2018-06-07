package com.slyak.smarto.service;

import com.google.common.collect.Maps;
import com.slyak.core.ssh2.SSH2;
import com.slyak.core.ssh2.SimpleStdCallback;
import com.slyak.core.ssh2.StdEventLogger;
import com.slyak.smarto.domain.Host;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * .
 *
 * @author stormning 2018/5/23
 * @since 1.3.0
 */
@Slf4j
public abstract class ScriptContexts implements ScriptContext {
    private final Set<String> scriptPaths;
    private final StdEventLogger stdCallback;
    private SSH2 ssh2;

    ScriptContexts(SSH2 ssh2, StdEventLogger stdLogger, Set<String> scriptPaths) {
        this.ssh2 = ssh2;
        this.scriptPaths = scriptPaths;
        this.stdCallback = stdLogger;
    }

    static ScriptContext select(Host host, SSH2 ssh2, StdEventLogger stdLogger, Set<String> scriptPaths) {
        return host.isTestHost() ?
                new DockerScriptRunnerContext(ssh2, stdLogger, scriptPaths, host) :
                new DefaultScriptRunnerContext(ssh2, stdLogger, scriptPaths);
    }

    @Override
    public Map<Long, Boolean> exec(List<Executable> scriptFiles) {
        Map<Long, Boolean> result = Maps.newConcurrentMap();
        try {
            for (Executable executable : scriptFiles) {
                try {
                    ssh2.execCommand(generateCommand(executable.getFile()), stdCallback);
                    result.put(executable.getScriptId(), !stdCallback.hasError());
                } catch (Exception e) {
                    log.error("Error occurred when execute remote script {}", executable);
                }
            }
        } finally {
            finishExec();
            ssh2.disconnect();
        }

        return result;
    }

    protected void finishExec() {
        //to implement it
    }

    abstract String generateCommand(String scriptFile);

    static class DefaultScriptRunnerContext extends ScriptContexts {

        DefaultScriptRunnerContext(SSH2 ssh2, StdEventLogger stdCallback, Set<String> scripts) {
            super(ssh2, stdCallback, scripts);
        }

        @Override
        String generateCommand(String scriptFile) {
            return "sh -c " + scriptFile;
        }
    }


    static class DockerScriptRunnerContext extends ScriptContexts {

        private final StdEventLogger stdLogger;
        private String container;
        private SSH2 ssh2;

        DockerScriptRunnerContext(SSH2 ssh2, StdEventLogger stdLogger, Set<String> filePaths, Host host) {
            super(ssh2, stdLogger, filePaths);
            this.stdLogger = stdLogger;
            this.container = RandomStringUtils.randomAlphabetic(6);
            this.ssh2 = ssh2;
            StringBuilder builder = new StringBuilder(
                    "docker run -idt" +
                            " --name " + container +
                            " --privileged=true" +
                            " -v /sys/fs/cgroup:/sys/fs/cgroup:ro"
            );
            for (String filePath : filePaths) {
                builder.append(" -v ").append(filePath).append(":").append(filePath);
            }
            builder.append(" ")
                    .append(host.getOsName())
                    .append(":")
                    .append(host.getOsVersion())
                    .append(" /usr/sbin/init");
            //createContainer
            stdLogger.info("Create docker container named : " + container + " for test purpose");
            ssh2.execCommand(builder.toString(), SimpleStdCallback.INSTANCE);
        }

        @Override
        String generateCommand(String scriptFile) {
            return "docker exec " + container + " " + scriptFile;
        }

        @Override
        protected void finishExec() {
            stdLogger.info("Destroy docker container named : " + container);
            ssh2.execCommand("docker rm -f " + container, SimpleStdCallback.INSTANCE);
        }
    }
}