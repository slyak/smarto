package com.slyak.mirrors.web;

import com.slyak.mirrors.domain.OS;
import com.slyak.mirrors.service.MirrorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * .
 *
 * @author stormning 2018/4/20
 * @since 1.3.0
 */
@Controller
@RequestMapping("/admin/*")
public class AdminController {

    @Autowired
    private MirrorManager mirrorManager;

    @GetMapping("/index")
    public void index() {
    }

    @GetMapping("/osList")
    public void osList() {

    }

    @GetMapping("/os")
    public void os(@RequestParam("id") OS os, ModelMap map) {
        map.put("os", os);
    }

    @PostMapping("/os")
    public void saveOs(OS os) {
        mirrorManager.saveOs(os);
    }


    @GetMapping("/files")
    public void files() {
    }
}