package com.slyak.mirrors.web;

import com.slyak.mirrors.domain.Project;
import com.slyak.mirrors.domain.ProjectGroup;
import com.slyak.mirrors.domain.ProjectGroupHostKey;
import com.slyak.mirrors.domain.ProjectGroupScript;
import com.slyak.mirrors.service.MirrorManager;
import com.slyak.web.support.data.RequestParamBind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return "redirect:/project/groups?id=" + project.getId();
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
        @GetMapping("/groups")
        public void groups(Long id, ModelMap modelMap) {
            modelMap.put("groups", mirrorManager.findProjectGroups(id));
        }

        @GetMapping("/group")
        public void group(@RequestParamBind("id") ProjectGroup projectGroup) {
        }

        @PostMapping("/group")
        public void saveGroup(@RequestParamBind("id") ProjectGroup projectGroup) {
            mirrorManager.saveProjectGroup(projectGroup);
        }

        @ModelAttribute("project")
        public Project getProject(@RequestParam(value = "id", required = false) Project project) {
            return project;
        }
    }


    @Controller
    @RequestMapping("/project/group/*")
    public static class ProjectGroupController {
        private final MirrorManager mirrorManager;

        @Autowired
        public ProjectGroupController(MirrorManager mirrorManager) {
            this.mirrorManager = mirrorManager;
        }

        @PostMapping("/orders")
        @ResponseBody
        public void orders(@RequestBody List<Long> groupIds) {
            mirrorManager.updateGroupOrders(groupIds);
        }

        @GetMapping("/hosts")
        public void hosts(Long id, ModelMap modelMap) {
            modelMap.put("groupHosts", mirrorManager.findProjectGroupHosts(id));
        }

        @GetMapping("/hostsPicker")
        public void hostsPicker(Long id, ModelMap modelMap) {
            modelMap.put("hosts", mirrorManager.findHostsNotInProjectGroup(id));
        }

        @PostMapping("/addHosts")
        @ResponseBody
        public void addHosts(Long groupId, @RequestParam("hostIds") List<Long> hostIds) {
            mirrorManager.addGroupHosts(groupId, hostIds);
        }

        @GetMapping("/deleteHost")
        @ResponseBody
        public void deleteHost(ProjectGroupHostKey id) {
            mirrorManager.deleteProjectGroupHost(id);
        }

        @GetMapping("/script")
        public void script(@RequestParamBind("id") ProjectGroupScript groupScript, ModelMap modelMap) {
            modelMap.put("groupScript", groupScript);
        }

        @PostMapping("/script")
        public void saveScript(@RequestParamBind("id") ProjectGroupScript groupScript) {
            mirrorManager.saveGroupScript(groupScript);
        }

        @GetMapping("/scripts")
        public void scripts(Long id, ModelMap modelMap) {
            modelMap.put("groupScripts", mirrorManager.findProjectGroupScripts(id));
        }

        @PostMapping("/scriptOrders")
        @ResponseBody
        public void scriptOrders(@RequestBody List<Long> groupScriptIds) {
            mirrorManager.updateGroupScriptOrders(groupScriptIds);
        }

        @GetMapping("/scriptsPicker")
        public void scriptsPicker(String keyword, Pageable pageable, ModelMap modelMap) {
            modelMap.put("page", mirrorManager.queryScripts(keyword, pageable));
        }


        @PostMapping("/addScripts")
        @ResponseBody
        public void addScripts(Long groupId, @RequestParam("scriptIds") List<Long> scriptIds) {
            mirrorManager.addGroupScripts(groupId, scriptIds);
        }

        @GetMapping("/deleteScript")
        @ResponseBody
        public void deleteScript(Long id) {
            mirrorManager.deleteProjectGroupScript(id);
        }

        @GetMapping("/envs")
        public void envs() {

        }

        @GetMapping("/delete")
        @ResponseBody
        public void deleteGroup(Long id) {
            mirrorManager.deleteProjectGroup(id);
        }

        @ModelAttribute("projectGroup")
        public ProjectGroup getProjectGroup(@RequestParam(value = "id", required = false) ProjectGroup projectGroup) {
            return projectGroup;
        }
    }
}