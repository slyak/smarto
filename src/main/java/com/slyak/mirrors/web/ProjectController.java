package com.slyak.mirrors.web;

import com.slyak.mirrors.domain.*;
import com.slyak.mirrors.service.ItasmManager;
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
    private ItasmManager itasmManager;

    @RequestMapping("/projects")
    public void index(Pageable pageable, ModelMap modelMap) {
        modelMap.put("page", itasmManager.queryProjects(pageable));
    }

    @GetMapping("/project")
    public void project(@RequestParamBind("id") Project project, ModelMap modelMap) {
        if (project != null) {
            modelMap.put("project", project);
        }
    }

    @PostMapping("/project")
    public String saveProject(@RequestParamBind("id") Project project) {
        itasmManager.saveProject(project);
        return "redirect:/project/groups?id=" + project.getId();
    }

    @Controller
    @RequestMapping("/project/*")
    public static class ProjectActionsController {
        private final ItasmManager itasmManager;

        @Autowired
        public ProjectActionsController(ItasmManager itasmManager) {
            this.itasmManager = itasmManager;
        }

        //group start
        @GetMapping("/groups")
        public void groups(Long id, ModelMap modelMap) {
            modelMap.put("groups", itasmManager.findProjectGroups(id));
        }

        @GetMapping("/group")
        public void group(@RequestParamBind("id") ProjectGroup projectGroup) {
        }

        @PostMapping("/group")
        public void saveGroup(@RequestParamBind("id") ProjectGroup projectGroup) {
            itasmManager.saveProjectGroup(projectGroup);
        }

        @ModelAttribute("project")
        public Project getProject(@RequestParam(value = "id", required = false) Project project) {
            return project;
        }
    }


    @Controller
    @RequestMapping("/project/group/*")
    public static class ProjectGroupController {
        private final ItasmManager itasmManager;

        @Autowired
        public ProjectGroupController(ItasmManager itasmManager) {
            this.itasmManager = itasmManager;
        }

        @PostMapping("/orders")
        @ResponseBody
        public void orders(@RequestBody List<Long> groupIds) {
            itasmManager.updateGroupOrders(groupIds);
        }

        @GetMapping("/hosts")
        public void hosts(Long id, ModelMap modelMap) {
            modelMap.put("groupHosts", itasmManager.findProjectGroupHosts(id));
        }

        @GetMapping("/hostsPicker")
        public void hostsPicker(Long id, ModelMap modelMap) {
            modelMap.put("hosts", itasmManager.findHostsNotInProjectGroup(id));
        }

        @PostMapping("/addHosts")
        @ResponseBody
        public void addHosts(Long groupId, @RequestParam("hostIds") List<Long> hostIds) {
            itasmManager.addGroupHosts(groupId, hostIds);
        }

        @GetMapping("/deleteHost")
        @ResponseBody
        public void deleteHost(ProjectGroupHostKey id) {
            itasmManager.deleteProjectGroupHost(id);
        }

        @GetMapping("/script")
        public void script(@RequestParamBind("id") ProjectGroupScript groupScript, ModelMap modelMap) {
            modelMap.put("groupScript", groupScript);
        }

        @PostMapping("/script")
        public void saveScript(@RequestParamBind("id") ProjectGroupScript groupScript) {
            itasmManager.saveGroupScript(groupScript);
        }

        @GetMapping("/scripts")
        public void scripts(Long id, ModelMap modelMap) {
            modelMap.put("groupScripts", itasmManager.findProjectGroupScripts(id));
        }

        @PostMapping("/scriptOrders")
        @ResponseBody
        public void scriptOrders(@RequestBody List<Long> groupScriptIds) {
            itasmManager.updateGroupScriptOrders(groupScriptIds);
        }

        @GetMapping("/scriptsPicker")
        public void scriptsPicker(String keyword, Pageable pageable, ModelMap modelMap) {
            modelMap.put("page", itasmManager.queryScripts(keyword, pageable));
        }

        @PostMapping("/addScripts")
        @ResponseBody
        public void addScripts(Long groupId, @RequestParam("scriptIds") List<Long> scriptIds) {
            itasmManager.addGroupScripts(groupId, scriptIds);
        }

        @GetMapping("/deleteScript")
        @ResponseBody
        public void deleteScript(Long id) {
            itasmManager.deleteProjectGroupScript(id);
        }

        @GetMapping("/envs")
        public void envs() {

        }

        @GetMapping("/delete")
        @ResponseBody
        public void deleteGroup(Long id) {
            itasmManager.deleteProjectGroup(id);
        }

        @GetMapping("/run")
        public String run(@RequestParam("id") Long id) {
            itasmManager.execOwnerScripts(BatchOwner.PROJECT_GROUP, id);
            return "redirect:/logs";
        }

        @ModelAttribute("projectGroup")
        public ProjectGroup getProjectGroup(@RequestParam(value = "id", required = false) ProjectGroup projectGroup) {
            return projectGroup;
        }
    }
}