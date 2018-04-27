package com.slyak.mirrors.web;

import com.slyak.mirrors.domain.Project;
import com.slyak.mirrors.service.MirrorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * .
 *
 * @author stormning 2018/4/16
 * @since 1.3.0
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private MirrorManager mirrorManager;

    @GetMapping("/projects")
    public void projects(Pageable pageable, ModelMap modelMap) {
        modelMap.put("page", mirrorManager.queryProjects(pageable));
    }

    @GetMapping("/groups")
    public void groups(Long projectId) {
    }


    @GetMapping("/group")
    public void group(Long groupId) {
    }

    @PostMapping
    public void saveProject(@Param("id") Project project) {
        groups(project.getId());
    }

    @GetMapping("/settings")
    public void settings() {
    }
}