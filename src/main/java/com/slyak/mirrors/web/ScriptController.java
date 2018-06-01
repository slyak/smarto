package com.slyak.mirrors.web;

import com.google.common.collect.Lists;
import com.slyak.mirrors.converter.OsVersionsOptionConverter;
import com.slyak.mirrors.domain.*;
import com.slyak.mirrors.service.MirrorManager;
import com.slyak.web.support.data.RequestParamBind;
import com.slyak.web.support.freemarker.bootstrap.InitialPreviewConfigConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        return "redirect:/script/content?id=" + script.getId();
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

        @GetMapping("/delete")
        @ResponseBody
        public boolean delete(Long id) {
            List<Project> projects = mirrorManager.findProjectsHavingScript(id);
            if (CollectionUtils.isEmpty(projects)) {
                mirrorManager.deleteScript(id);
                return true;
            }
            return false;
        }

        @GetMapping("/osVersions")
        public void osVersions(String osName, ModelMap modelMap) {
            OS os = mirrorManager.findOs(osName);
            if (os != null) {
                modelMap.put("versions", os.getVersions());
            }
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

        @GetMapping("/file/delete")
        @ResponseBody
        public void deleteFile(@RequestParamBind("id") ScriptFile scriptFile) {
            mirrorManager.deleteScriptFile(scriptFile);
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
        public void env(@RequestParamBind("scriptId") Script script, String key, ModelMap modelMap) {
            List<ScriptEnv> envs = Optional.ofNullable(script.getEnvs()).orElseGet(Lists::newArrayList);
            for (int i = envs.size() - 1; i >= 0; i--) {
                ScriptEnv env = envs.get(i);
                if (Objects.equals(env.getKey(), key)) {
                    modelMap.put("env", env);
                    break;
                }
            }
        }

        @PostMapping("/env")
        public void saveEnv(@RequestParamBind("scriptId") Script script, ScriptEnv scriptEnv) {
            List<ScriptEnv> envs = Optional.ofNullable(script.getEnvs()).orElseGet(Lists::newArrayList);
            boolean exist = false;
            for (int i = envs.size() - 1; i >= 0; i--) {
                ScriptEnv env = envs.get(i);
                if (Objects.equals(env.getKey(), scriptEnv.getKey())) {
                    exist = true;
                    env.setDescription(scriptEnv.getDescription());
                    env.setDefValue(scriptEnv.getDefValue());
                }
            }
            if (!exist) {
                envs.add(scriptEnv);
            }
            mirrorManager.saveScript(script);
        }

        @GetMapping("/env/delete")
        @ResponseBody
        public boolean envDelete(@RequestParamBind("scriptId") Script script, String key) {
            List<ScriptEnv> envs = Optional.ofNullable(script.getEnvs()).orElseGet(Lists::newArrayList);
            for (int i = envs.size() - 1; i >= 0; i--) {
                ScriptEnv env = envs.get(i);
                if (Objects.equals(env.getKey(), key)) {
                    envs.remove(i);
                }
            }
            mirrorManager.saveScript(script);
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
}
