package com.slyak.mirrors.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * .
 *
 * @author stormning 2018/5/2
 * @since 1.3.0
 */
@Controller
public class ScriptController {

    @GetMapping("/scripts")
    public void scripts(String keyword) {

    }

    @GetMapping("/script/picker")
    public void picker(String keyword) {

    }

    @GetMapping("/script")
    public void script(String keyword) {

    }

    @GetMapping("/script/{id}")
    public void settings(String keyword) {

    }

    @GetMapping("/script/files")
    public void files(String keyword) {

    }

    @GetMapping("/script/file")
    public void file() {

    }

    @GetMapping("/script/envs")
    public void envs() {

    }

    @GetMapping("/script/logs")
    public void logs() {

    }

    @GetMapping("/script/env")
    public void env() {

    }

    @GetMapping("/script/help")
    public void help() {

    }

    @GetMapping("/script/settings")
    public void settings() {

    }
}