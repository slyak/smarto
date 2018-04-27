package com.slyak.mirrors.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * .
 *
 * @author stormning 2018/4/27
 * @since 1.3.0
 */
@Controller
@RequestMapping("/mirror")
public class MirrorController {

    @GetMapping("/mirrors")
    public void mirrors(String keyword) {

    }
}
