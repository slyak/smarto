package com.slyak.mirrors.web;

import com.google.common.collect.Lists;
import com.slyak.core.ssh2.SSH2;
import com.slyak.mirrors.converter.OsVersionsOptionConverter;
import com.slyak.mirrors.domain.*;
import com.slyak.mirrors.service.MirrorManager;
import com.slyak.web.support.data.RequestParamBind;
import com.slyak.web.support.freemarker.bootstrap.InitialPreviewConfigConverter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;

/**
 * .
 *
 * @author stormning 2018/5/11
 * @since 1.3.0
 */
@Controller
public class ScriptController {
    private final MirrorManager mirrorManager;

    private final OsVersionsOptionConverter optionConverter;

    @Autowired
    public ScriptController(MirrorManager mirrorManager, OsVersionsOptionConverter optionConverter) {
        this.mirrorManager = mirrorManager;
        this.optionConverter = optionConverter;
    }

    @GetMapping("/scripts")
    public void scripts(String keyword, Pageable pageable, ModelMap modelMap) {
        modelMap.put("page", mirrorManager.queryScripts(keyword, pageable));
    }

    @GetMapping("/scriptPicker")
    public void picker(String keyword) {

    }

    @GetMapping("/script")
    public void script(@RequestParamBind("id") Script script, ModelMap modelMap) {
        modelMap.put("oss", optionConverter.convert(mirrorManager.queryOss()));
        if (script != null) {
            modelMap.put("script", script);
        }
    }

    @PostMapping("/script")
    public String saveScript(@RequestParamBind("id") Script script) {
        mirrorManager.saveScript(script);
        return "redirect:/script/files?id=" + script.getId();
    }


    @Controller
    @RequestMapping("/script/*")
    public static class ScriptActionController {

        private final MirrorManager mirrorManager;

        private final InitialPreviewConfigConverter<GlobalFile> converter;

        @Autowired
        public ScriptActionController(MirrorManager mirrorManager, InitialPreviewConfigConverter<GlobalFile> converter) {
            this.mirrorManager = mirrorManager;
            this.converter = converter;
        }

        @GetMapping("/osVersions")
        public void osVersions(String osName, ModelMap modelMap) {
            OS os = mirrorManager.findOs(osName);
            if (os != null) {
                modelMap.put("versions", os.getVersions());
            }
        }

        @GetMapping("/settings")
        public void settings() {
        }

        @GetMapping("/files")
        public void files(Long id, ModelMap modelMap) {
            List<ScriptFile> files = mirrorManager.findScriptFiles(id);
            modelMap.put("files", files);
        }

        @GetMapping("/file")
        public void file(@RequestParamBind("id") ScriptFile scriptFile, ModelMap modelMap) {
            if (scriptFile != null) {
                GlobalFile globalFile = scriptFile.getGlobalFile();
                if (globalFile != null) {
                    modelMap.put("initConfig", converter.convert(Lists.newArrayList(globalFile)));
                }
            }
            modelMap.put("scriptFile", scriptFile);
        }

        @PostMapping("/file")
        @ResponseBody
        public void saveFile(@RequestParamBind("id") ScriptFile scriptFile) {
            mirrorManager.saveScriptFile(scriptFile);
        }

        @GetMapping("/envs")
        public void envs(ModelMap modelMap) {
        }

        @PostMapping("/envs")
        public String saveEnvs(@RequestParamBind("id") Script script) {
            mirrorManager.saveScript(script);
            return "redirect:/script/envs?id=" + script.getId();
        }

        @GetMapping("/env")
        public void env() {

        }

        @GetMapping("/env/delete")
        @ResponseBody
        public boolean envDelete(@RequestParamBind("scriptId") Script script, String key) {
            List<ScriptEnv> envs = script.getEnvs();
            if (!CollectionUtils.isEmpty(envs)) {
                for (int i = envs.size() - 1; i >= 0; i--) {
                    ScriptEnv env = envs.get(i);
                    if (Objects.equals(env.getKey(), key)){
                        envs.remove(i);
                    }
                }
                mirrorManager.saveScript(script);
            }
            return true;
        }

        @GetMapping("/help")
        public void help() {

        }

        @GetMapping("/content")
        public void content() {

        }

        @GetMapping("/run")
        @ResponseBody
        public Long run(@RequestParam("id") Long id) {
            Batch batch = mirrorManager.execScript(id);
            return batch.getId();
        }

        @ModelAttribute("script")
        public Script getScript(@RequestParam(value = "id", required = false) Script script) {
            return script;
        }

    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        SSH2 ssh2 = SSH2.connect("192.168.230.8", 22).auth("root", "123456");
        String name = RandomStringUtils.randomAlphanumeric(6);
        String osName = "centos";
        String osVersion = "7";
        String test = "#!/bin/sh\r\n" +
                "mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.backup\r\n" +
                "wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo";
        String test2 = StringUtils.chomp(test);

        /*ssh2.scp(new ByteArrayInputStream(test.getBytes("UTF-8")), "hello.sh", "/opt/test");
        ssh2.execCommand("docker run -idt -v /opt/test:/opt/test --name " + name + " " + osName + ":" + osVersion, SimpleStdCallback.INSTANCE);
        ssh2.execCommand("docker exec " + name + " /opt/test/hello.sh", SimpleStdCallback.INSTANCE);*/
    }
}
