package com.slyak.mirrors.service;

import com.slyak.core.ssh2.SSH2;
import com.slyak.core.ssh2.StdCallback;
import com.slyak.mirrors.domain.Host;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
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
    private final StdCallback stdCallback;
    SSH2 ssh2;

    ScriptContexts(SSH2 ssh2, StdCallback stdCallback, Set<String> scriptPaths) {
        this.ssh2 = ssh2;
        this.scriptPaths = scriptPaths;
        this.stdCallback = stdCallback;
    }

    @Override
    public void exec(List<String> scriptFiles) {
        try {
            for (String scriptFile : scriptFiles) {
                try {
                    ssh2.execCommand(generateCommand(scriptFile), stdCallback);
                } catch (Exception e) {
                    log.error("Error occurred when execute scriptFile {}", scriptFile);
                }
            }
        } finally {
            finishExec();
            ssh2.disconnect();
        }

    }

    protected void finishExec() {
        //to implement it
    }

    abstract String generateCommand(String scriptFile);


    static ScriptContext select(Host host, SSH2 ssh2, StdCallback callback, Set<String> scriptPaths) {
        return host.isTestHost() ?
                new DockerScriptRunnerContext(ssh2, callback, scriptPaths, host) :
                new DefaultScriptRunnerContext(ssh2, callback, scriptPaths);
    }


    static class DefaultScriptRunnerContext extends ScriptContexts {

        DefaultScriptRunnerContext(SSH2 ssh2, StdCallback stdCallback, Set<String> scripts) {
            super(ssh2, stdCallback, scripts);
        }

        @Override
        String generateCommand(String scriptFile) {
            return "sh -c " + scriptFile;
        }
    }


    static class DockerScriptRunnerContext extends ScriptContexts {

        private final StdCallback callback;
        private String container;

        DockerScriptRunnerContext(SSH2 ssh2, StdCallback callback, Set<String> filePaths, Host host) {
            super(ssh2, callback, filePaths);
            this.callback = callback;
            this.container = RandomStringUtils.randomAlphabetic(6);
            StringBuilder builder = new StringBuilder("docker run -idt --name " + container);
            for (String filePath : filePaths) {
                builder.append(" -v ").append(filePath).append(":").append(filePath);
            }
            builder.append(" ").append(host.getOsName()).append(":").append(host.getOsVersion());

            //createContainer
            ssh2.execCommand(builder.toString(), callback);
        }

        @Override
        String generateCommand(String scriptFile) {
            return "docker exec " + container + " " + scriptFile;
        }

        @Override
        protected void finishExec() {
//            ssh2.execCommand("docker rm -f " + container, callback);
        }
    }
}