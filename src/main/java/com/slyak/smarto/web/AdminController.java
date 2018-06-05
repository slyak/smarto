package com.slyak.smarto.web;

import com.slyak.smarto.domain.Host;
import com.slyak.smarto.domain.OS;
import com.slyak.smarto.service.SmartoManager;
import com.slyak.web.support.data.RequestParamBind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private SmartoManager smartoManager;

    @GetMapping("/index")
    public void index(ModelMap modelMap) {
        modelMap.put("global", smartoManager.findGlobal());
    }

    @GetMapping("/oss")
    public void oss(ModelMap modelMap) {
        modelMap.put("oss", smartoManager.queryOss());
    }

    @GetMapping("/os")
    public void os(@RequestParamBind("id") OS os, ModelMap map) {
        map.put("os", os);
    }

    @PostMapping("/os")
    public void saveOs(@RequestParamBind("id") OS os) {
        smartoManager.saveOs(os);
    }

    @GetMapping("/testHost")
    @ResponseBody
    public boolean validateTestHost() {
        Host testHost = smartoManager.getTestHost();
        return smartoManager.validateHost(testHost, "docker -v", "version");
    }

    @GetMapping("/files")
    public void files() {
    }

    @GetMapping("/sysEnvs")
    public void sysEnvs(ModelMap modelMap) {
        modelMap.put("sysEnvs", smartoManager.querySysEnvs());
    }
}