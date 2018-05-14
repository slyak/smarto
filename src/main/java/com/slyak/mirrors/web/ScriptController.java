package com.slyak.mirrors.web;

import com.google.common.collect.Lists;
import com.slyak.mirrors.domain.GlobalFile;
import com.slyak.mirrors.domain.Script;
import com.slyak.mirrors.domain.ScriptEnv;
import com.slyak.mirrors.domain.ScriptFile;
import com.slyak.mirrors.service.MirrorManager;
import com.slyak.web.support.data.RequestParamBind;
import com.slyak.web.support.freemarker.bootstrap.InitialPreviewConfigConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
    public void envs(Long scriptId, ModelMap modelMap) {
        List<ScriptEnv> envs = mirrorManager.findScriptEnvs(scriptId);
        modelMap.put("envs", envs);
    }

    @GetMapping("/logs")
    public void logs() {

    }

    @GetMapping("/env")
    public void env() {

    }

    @GetMapping("/help")
    public void help(Long scriptId) {

    }

    @ModelAttribute("script")
    public Script getScript(@RequestParam(value = "id", required = false) Script script) {
        return script;
    }
}
