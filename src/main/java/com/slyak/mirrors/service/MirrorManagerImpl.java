package com.slyak.mirrors.service;

import com.slyak.mirrors.domain.Project;
import com.slyak.mirrors.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * .
 *
 * @author stormning 2018/4/17
 * @since 1.3.0
 */
@Service
public class MirrorManagerImpl implements MirrorManager {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Page<Project> queryProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }
}