package com.slyak.mirrors.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * .
 *
 * @author stormning 2018/4/20
 * @since 1.3.0
 */
@Controller
@RequestMapping("/global")
public class GlobalController {

    @GetMapping
    public void global() {
    }

    @PostMapping
    public void saveGlobal() {
        global();
    }
}
