package com.slyak.mirrors.web;

import com.slyak.mirrors.service.MirrorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * .
 *
 * @author stormning 2018/4/16
 * @since 1.3.0
 */
@Controller
public class IndexController {

    @Autowired
    private MirrorManager mirrorManager;

    @GetMapping("/")
    public String index(Pageable pageable, ModelMap modelMap) {
        modelMap.put("page", mirrorManager.queryProjects(pageable));
        return "projects";
    }

    @GetMapping("/project")
    public void project() {
    }

    @PostMapping("/project")
    public void saveProject() {
        project();
    }

    @GetMapping("/group")
    public void group() {
    }

    @PostMapping("/group")
    public void saveGroup() {
        group();
    }

    @GetMapping("/global")
    public void global() {
    }

    @PostMapping("/global")
    public void saveGlobal() {
        global();
    }
}