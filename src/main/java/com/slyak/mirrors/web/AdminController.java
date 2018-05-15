package com.slyak.mirrors.web;

import com.slyak.mirrors.domain.OS;
import com.slyak.mirrors.service.MirrorManager;
import com.slyak.web.support.data.RequestParamBind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
@RequestMapping("/admin/*")
public class AdminController {

    @Autowired
    private MirrorManager mirrorManager;

    @GetMapping("/index")
    public void index() {
    }

    @GetMapping("/oss")
    public void oss(ModelMap modelMap) {
        modelMap.put("oss", mirrorManager.queryOss());
    }

    @GetMapping("/os")
    public void os(@RequestParamBind("id") OS os, ModelMap map) {
        map.put("os", os);
    }

    @PostMapping("/os")
    public void saveOs(@RequestParamBind("id") OS os) {
        mirrorManager.saveOs(os);
    }


    @GetMapping("/files")
    public void files() {
    }
}