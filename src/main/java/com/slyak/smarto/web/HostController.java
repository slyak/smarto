package com.slyak.smarto.web;

import com.slyak.smarto.converter.OsVersionsOptionConverter;
import com.slyak.smarto.domain.Host;
import com.slyak.smarto.domain.OS;
import com.slyak.smarto.service.SmartoManager;
import com.slyak.web.support.data.RequestParamBind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * .
 *
 * @author stormning 2018/5/22
 * @since 1.3.0
 */
@Controller
public class HostController {

    private final SmartoManager smartoManager;

    private final OsVersionsOptionConverter optionConverter;

    @Autowired
    public HostController(SmartoManager smartoManager, OsVersionsOptionConverter optionConverter) {
        this.smartoManager = smartoManager;
        this.optionConverter = optionConverter;
    }

    @RequestMapping("/hosts")
    public void hosts(Pageable pageable, ModelMap modelMap) {
        modelMap.put("page", smartoManager.queryHosts(pageable));
    }

    @GetMapping("/host")
    public void host(@RequestParamBind("id") Host host, ModelMap modelMap) {
        modelMap.put("oss", optionConverter.convert(smartoManager.queryOss()));
        if (host != null) {
            modelMap.put("script", host);
        }
    }

    @PostMapping("/host")
    public String saveHost(@RequestParamBind("id") Host host) {
        smartoManager.saveHost(host);
        return "redirect:/hosts";
    }

    @Controller
    @RequestMapping("/host/*")
    public static class HostActionController {

        private final SmartoManager smartoManager;

        @Autowired
        public HostActionController(SmartoManager smartoManager) {
            this.smartoManager = smartoManager;
        }

        @RequestMapping("/test")
        @ResponseBody
        public boolean test(@RequestParam(value = "id", required = false) Host host) {
            return smartoManager.validateHost(host);
        }

        @GetMapping("/osVersion")
        public void osVersions(String osName, ModelMap modelMap) {
            OS os = smartoManager.findOs(osName);
            if (os != null) {
                modelMap.put("versions", os.getVersions());
            }
        }

        @ModelAttribute("host")
        public Host getHost(@RequestParam(value = "id", required = false) Host host) {
            return host;
        }
    }
}
