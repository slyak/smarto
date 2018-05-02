package com.slyak.mirrors.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * .
 *
 * @author stormning 2018/5/2
 * @since 1.3.0
 */
@Controller
@RequestMapping("/script")
public class ScriptController {

    @GetMapping("/scripts")
    public void mirrors(String keyword) {

    }
}
