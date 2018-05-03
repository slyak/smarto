package com.slyak.mirrors.web;

import com.slyak.mirrors.service.MirrorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * .
 *
 * @author stormning 2018/4/16
 * @since 1.3.0
 */
@Controller
@RequestMapping("/project/*")
public class ProjectScriptController {

    @Autowired
    private MirrorManager mirrorManager;

    @GetMapping("/scripts")
    public void scripts() {
    }

    @GetMapping("/script")
    public void script() {
    }

    @GetMapping("/script/{id}")
    public void settings() {
    }
}