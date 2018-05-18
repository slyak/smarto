package com.slyak.mirrors.web;

import com.google.common.collect.Lists;
import com.slyak.mirrors.domain.*;
import com.slyak.mirrors.service.MirrorManager;
import com.slyak.web.support.data.RequestParamBind;
import com.slyak.web.support.freemarker.bootstrap.InitialPreviewConfigConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * .
 *
 * @author stormning 2018/5/11
 * @since 1.3.0
 */
@Controller
@RequestMapping("/script/*")
public class ScriptController {
    @Autowired
    private MirrorManager mirrorManager;

    @Autowired
    private InitialPreviewConfigConverter<GlobalFile> converter;

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

    @GetMapping("/logs")
    public void logs() {

    }

    @GetMapping("/help")
    public void help() {

    }

    @GetMapping("/content")
    public void content() {

    }

    @GetMapping("/run")
    public void run(@RequestParam("id") Long id) {
        mirrorManager.testExecScript(id);
    }

    @ModelAttribute("script")
    public Script getScript(@RequestParam(value = "id", required = false) Script script) {
        return script;
    }
}
