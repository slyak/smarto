package com.slyak.smarto.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * .
 *
 * @author stormning 2018/4/27
 * @since 1.3.0
 */
@Controller
public class MirrorController {

    @GetMapping("/mirrors")
    public void mirrors(String keyword) {

    }
}