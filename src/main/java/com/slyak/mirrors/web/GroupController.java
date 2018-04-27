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

    @GetMapping("/hosts")
    public void hosts() {
    }

    @GetMapping("/host")
    public void host() {
    }

    @GetMapping("/dependency")
    public void dependency() {

    }

    @GetMapping("/dependencies")
    public void dependencies() {

    }

    @PostMapping
    public void saveGroup() {
        hosts();
    }


    @GetMapping("/files")
    public void files() {
    }

    @GetMapping("/file")
    public void file() {
    }

    @GetMapping("/script")
    public void script() {
    }


    @GetMapping("/scripts")
    public void scripts() {
    }

    @GetMapping("/envs")
    public void envs() {
    }

    @GetMapping("/env")
    public void env() {
    }

    @GetMapping("/settings")
    public void settings() {
    }
}