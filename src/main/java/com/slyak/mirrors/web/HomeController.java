package com.slyak.mirrors.web;

import com.slyak.mirrors.domain.OS;
import com.slyak.mirrors.domain.Script;
import com.slyak.mirrors.service.MirrorManager;
import com.slyak.web.support.data.RequestParamBind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * .
 *
 * @author stormning 2018/4/20
 * @since 1.3.0
 */
@Controller
public class HomeController {

    @Autowired
    private MirrorManager mirrorManager;

    @RequestMapping("")
    public String home() {
        return "redirect:/projects";
    }

    @GetMapping("/scripts")
    public void scripts(String keyword, Pageable pageable, ModelMap modelMap) {
        modelMap.put("page", mirrorManager.queryScripts(keyword, pageable));
    }

    @GetMapping("/scriptPicker")
    public void picker(String keyword) {

    }

    @GetMapping("/script")
    public void script(ModelMap modelMap) {
        modelMap.put("os",mirrorManager.queryOss());
    }

    @PostMapping("/script")
    public String saveScript(@RequestParamBind("id") Script script) {
        mirrorManager.saveScript(script);
        return "redirect:/script/files?id=" + script.getId();
    }
}