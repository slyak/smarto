package com.slyak.mirrors.web;

import com.slyak.mirrors.domain.ScriptEnv;
import com.slyak.mirrors.domain.ScriptFile;
import com.slyak.mirrors.service.MirrorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * .
 *
 * @author stormning 2018/5/2
 * @since 1.3.0
 */
@Controller
public class ScriptController {

    @Autowired
    private MirrorManager mirrorManager;

    @GetMapping("/scripts")
    public void scripts(String keyword) {

    }

    @GetMapping("/script/picker")
    public void picker(String keyword) {

    }

    @GetMapping("/script")
    public void script() {

    }

    @GetMapping("/script/{id}")
    public void settings(String keyword) {

    }

    @GetMapping("/script/files")
    public void files(Long scriptId, ModelMap modelMap) {
        List<ScriptFile> files = mirrorManager.findScriptFiles(scriptId);
        modelMap.put("files", files);
    }

    @GetMapping("/script/file")
    public void file() {

    }

    @GetMapping("/script/file/add")
    public void fileAdd() {

    }

    @GetMapping("/script/envs")
    public void envs(Long scriptId, ModelMap modelMap) {
        List<ScriptEnv> envs = mirrorManager.findScriptEnvs(scriptId);
        modelMap.put("envs", envs);
    }

    @GetMapping("/script/logs")
    public void logs() {

    }

    @GetMapping("/script/env")
    public void env() {

    }

    @GetMapping("/script/help")
    public void help(Long scriptId) {

    }

    @GetMapping("/script/showHelp")
    public void showHelp(Long scriptId) {

    }

    @GetMapping("/script/settings")
    public void settings() {

    }
}