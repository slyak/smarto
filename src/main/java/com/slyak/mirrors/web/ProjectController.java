package com.slyak.mirrors.web;

import com.slyak.mirrors.domain.Project;
import com.slyak.mirrors.domain.ProjectRole;
import com.slyak.mirrors.service.MirrorManager;
import com.slyak.web.support.data.RequestParamBind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * .
 *
 * @author stormning 2018/4/16
 * @since 1.3.0
 */
@Controller
public class ProjectController {

    @Autowired
    private MirrorManager mirrorManager;

    @RequestMapping("/projects")
    public void index(Pageable pageable, ModelMap modelMap) {
        modelMap.put("page", mirrorManager.queryProjects(pageable));
    }

    @GetMapping("/project")
    public void project(@RequestParamBind("id") Project project, ModelMap modelMap) {
        if (project != null) {
            modelMap.put("project", project);
        }
    }

    @PostMapping("/project")
    public String saveProject(@RequestParamBind("id") Project project) {
        mirrorManager.saveProject(project);
        return "redirect:/project/roles?id=" + project.getId();
    }

    @Controller
    @RequestMapping("/project/*")
    public static class ProjectActionsController {
        private final MirrorManager mirrorManager;

        @Autowired
        public ProjectActionsController(MirrorManager mirrorManager) {
            this.mirrorManager = mirrorManager;
        }

        //group start
        @GetMapping("/roles")
        public void roles(Long id, ModelMap modelMap) {
            modelMap.put("roles", mirrorManager.findProjectRoles(id));
        }

        @GetMapping("/role")
        public void role(@RequestParamBind("id") ProjectRole projectRole) {

        }

        @PostMapping("/role")
        public void saveRole(@RequestParamBind("id") ProjectRole projectRole) {
            mirrorManager.saveProjectRole(projectRole);
        }

        @ModelAttribute("project")
        public Project getProject(@RequestParam(value = "id", required = false) Project project) {
            return project;
        }
    }


    @Controller
    @RequestMapping("/project/role/*")
    public static class ProjectRoleController {
        private final MirrorManager mirrorManager;

        @Autowired
        public ProjectRoleController(MirrorManager mirrorManager) {
            this.mirrorManager = mirrorManager;
        }

        @GetMapping("/hosts")
        public void hosts() {

        }

        @GetMapping("/scripts")
        public void scripts() {

        }

        @GetMapping("/envs")
        public void envs() {

        }

        @GetMapping("/hostsPicker")
        public void hostsPicker(Long id, ModelMap modelMap) {
            mirrorManager.findHostsNotInProjectRole(id);
        }

        @ModelAttribute("projectRole")
        public ProjectRole getProjectRole(@RequestParam(value = "id", required = false) ProjectRole projectRole) {
            return projectRole;
        }
    }
}