package com.slyak.smarto.web;

import com.google.common.collect.Lists;
import com.slyak.smarto.converter.OsVersionsOptionConverter;
import com.slyak.smarto.domain.*;
import com.slyak.smarto.service.SmartoManager;
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
    private final SmartoManager smartoManager;

    private final OsVersionsOptionConverter optionConverter;

    @Autowired
    public ScriptController(SmartoManager smartoManager, OsVersionsOptionConverter optionConverter) {
        this.smartoManager = smartoManager;
        this.optionConverter = optionConverter;
    }

    @GetMapping("/scripts")
    public void scripts(String keyword, Pageable pageable, ModelMap modelMap) {
        modelMap.put("page", smartoManager.queryScripts(keyword, pageable));
    }

    @GetMapping("/scriptPicker")
    public void picker(String keyword) {

    }

    @GetMapping("/script")
    public void script(@RequestParamBind("id") Script script, ModelMap modelMap) {
        modelMap.put("oss", optionConverter.convert(smartoManager.queryOss()));
        if (script != null) {
            modelMap.put("script", script);
        }
    }

    @PostMapping("/script")
    public String saveScript(@RequestParamBind("id") Script script) {
        smartoManager.saveScript(script);
        return "redirect:/script/content?id=" + script.getId();
    }

    @Controller
    @RequestMapping("/script/*")
    public static class ScriptActionController {

        private final SmartoManager smartoManager;

        private final InitialPreviewConfigConverter<GlobalFile> converter;

        @Autowired
        public ScriptActionController(SmartoManager smartoManager, InitialPreviewConfigConverter<GlobalFile> converter) {
            this.smartoManager = smartoManager;
            this.converter = converter;
        }

        @GetMapping("/delete")
        @ResponseBody
        public boolean delete(Long id) {
            List<Project> projects = smartoManager.findProjectsHavingScript(id);
            if (CollectionUtils.isEmpty(projects)) {
                smartoManager.deleteScript(id);
                return true;
            }
            return false;
        }

        @GetMapping("/osVersions")
        public void osVersions(String osName, ModelMap modelMap) {
            OS os = smartoManager.findOs(osName);
            if (os != null) {
                modelMap.put("versions", os.getVersions());
            }
        }

        @GetMapping("/files")
        public void files(Long id, ModelMap modelMap) {
            List<ScriptFile> files = smartoManager.findScriptFiles(id);
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
            smartoManager.saveScriptFile(scriptFile);
        }

        @GetMapping("/file/delete")
        @ResponseBody
        public void deleteFile(@RequestParamBind("id") ScriptFile scriptFile) {
            smartoManager.deleteScriptFile(scriptFile);
        }

        @GetMapping("/envs")
        public void envs(ModelMap modelMap) {
        }

        @PostMapping("/envs")
        public String saveEnvs(@RequestParamBind("id") Script script) {
            smartoManager.saveScript(script);
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
            smartoManager.saveScript(script);
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
            smartoManager.saveScript(script);
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
        public void run(@RequestParam("id") Long id) {
            smartoManager.execOwnerScripts(BatchOwner.SCRIPT, id);
        }

        @ModelAttribute("script")
        public Script getScript(@RequestParam(value = "id", required = false) Script script) {
            return script;
        }

    }
}
