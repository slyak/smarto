package com.slyak.mirrors.web;

import com.slyak.mirrors.service.MirrorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * .
 *
 * @author stormning 2018/4/16
 * @since 1.3.0
 */
@Controller
@RequestMapping("/project/*")
public class ProjectGroupController {

    @Autowired
    private MirrorManager mirrorManager;

    @GetMapping("/groups")
    public void groups(Pageable pageable, ModelMap modelMap) {
        modelMap.put("page", mirrorManager.queryProjects(pageable));
    }

    @GetMapping("/group")
    public void group(Long projectId) {

    }

    @GetMapping("/logs")
    public void logs(Long projectId) {

    }

    @GetMapping("/group/{id}")
    public void settings() {
    }
}