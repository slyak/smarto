package com.slyak.mirrors.web;

import com.slyak.mirrors.service.MirrorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * .
 *
 * @author stormning 2018/4/16
 * @since 1.3.0
 */
@Controller
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private MirrorManager mirrorManager;

    @GetMapping
    public String group() {
        return "redirect:/group/hosts";
    }

    @GetMapping("/hosts")
    public void hosts() {
    }


    @PostMapping
    public void saveGroup() {
        group();
    }


    @GetMapping("/files")
    public void files() {
    }

    @GetMapping("/scripts")
    public void scripts() {
    }

    @GetMapping("/envs")
    public void envs() {
    }
}