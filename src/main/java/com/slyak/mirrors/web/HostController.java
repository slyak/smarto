package com.slyak.mirrors.web;

import com.slyak.mirrors.converter.OsVersionsOptionConverter;
import com.slyak.mirrors.domain.Host;
import com.slyak.mirrors.domain.OS;
import com.slyak.mirrors.service.ItasmManager;
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

    private final ItasmManager itasmManager;

    private final OsVersionsOptionConverter optionConverter;

    @Autowired
    public HostController(ItasmManager itasmManager, OsVersionsOptionConverter optionConverter) {
        this.itasmManager = itasmManager;
        this.optionConverter = optionConverter;
    }

    @RequestMapping("/hosts")
    public void hosts(Pageable pageable, ModelMap modelMap) {
        modelMap.put("page", itasmManager.queryHosts(pageable));
    }

    @GetMapping("/host")
    public void host(@RequestParamBind("id") Host host, ModelMap modelMap) {
        modelMap.put("oss", optionConverter.convert(itasmManager.queryOss()));
        if (host != null) {
            modelMap.put("script", host);
        }
    }

    @PostMapping("/host")
    public String saveHost(@RequestParamBind("id") Host host) {
        itasmManager.saveHost(host);
        return "redirect:/hosts";
    }

    @Controller
    @RequestMapping("/host/*")
    public static class HostActionController {

        private final ItasmManager itasmManager;

        @Autowired
        public HostActionController(ItasmManager itasmManager) {
            this.itasmManager = itasmManager;
        }

        @RequestMapping("/test")
        @ResponseBody
        public boolean test(@RequestParam(value = "id", required = false) Host host) {
            return itasmManager.validateHost(host);
        }

        @GetMapping("/osVersion")
        public void osVersions(String osName, ModelMap modelMap) {
            OS os = itasmManager.findOs(osName);
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
