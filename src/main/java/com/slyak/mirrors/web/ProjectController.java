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
public class ProjectController {

    @Autowired
    private MirrorManager mirrorManager;

    @RequestMapping("/projects")
    public void index(Pageable pageable, ModelMap modelMap) {
        modelMap.put("page", mirrorManager.queryProjects(pageable));
    }

    @GetMapping("/project")
    public void project(Long projectId) {

    }

    @GetMapping("/project/{id}")
    public void settings() {
    }


    @Controller
    @RequestMapping("/project/*")
    public static class ProjectActionsController {
        @Autowired
        private MirrorManager mirrorManager;

        //host start

        @GetMapping("/hosts")
        public void hosts(Pageable pageable, ModelMap modelMap) {
            modelMap.put("page", mirrorManager.queryProjects(pageable));
        }

        @GetMapping("/host")
        public void host() {
        }

        @GetMapping("/host/{id}")
        public void hostSettings() {
        }


        //group start

        @GetMapping("/groups")
        public void groups(Pageable pageable, ModelMap modelMap) {
            modelMap.put("page", mirrorManager.queryProjects(pageable));
        }

        @GetMapping("/group")
        public void group(Long projectId) {

        }

        @GetMapping("/group/hosts")
        public void hosts(Long projectId) {

        }

        @GetMapping("/group/{id}")
        public void groupSettings() {
        }

        //script start
        @GetMapping("/scripts")
        public void scripts() {
        }

        @GetMapping("/script")
        public void script() {
        }

        @GetMapping("/script/{id}")
        public void settings() {
        }
    }
}