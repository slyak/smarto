package com.slyak.mirrors.web;

import com.slyak.mirrors.domain.Host;
import com.slyak.mirrors.domain.OS;
import com.slyak.mirrors.service.ItasmManager;
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
    private ItasmManager itasmManager;

    @GetMapping("/index")
    public void index(ModelMap modelMap) {
        modelMap.put("global", itasmManager.findGlobal());
    }

    @GetMapping("/oss")
    public void oss(ModelMap modelMap) {
        modelMap.put("oss", itasmManager.queryOss());
    }

    @GetMapping("/os")
    public void os(@RequestParamBind("id") OS os, ModelMap map) {
        map.put("os", os);
    }

    @PostMapping("/os")
    public void saveOs(@RequestParamBind("id") OS os) {
        itasmManager.saveOs(os);
    }

    @GetMapping("/testHost")
    @ResponseBody
    public boolean validateTestHost() {
        Host testHost = itasmManager.getTestHost();
        return itasmManager.validateHost(testHost, "docker -v", "version");
    }

    @GetMapping("/files")
    public void files() {
    }

    @GetMapping("/sysEnvs")
    public void sysEnvs(ModelMap modelMap) {
        modelMap.put("sysEnvs", itasmManager.querySysEnvs());
    }
}