package com.slyak.mirrors.assembler;

import com.slyak.mirrors.domain.Project;
import com.slyak.mirrors.domain.ProjectRole;
import com.slyak.mirrors.repository.ProjectRepository;
import com.slyak.spring.jpa.EntityAssemblerOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * .
 *
 * @author stormning 2018/5/28
 * @since 1.3.0
 */
@Component
public class ProjectRoleProjectAssembler extends EntityAssemblerOne<ProjectRole, Long, Project> {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectRoleProjectAssembler(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    protected Long getKey(ProjectRole bean) {
        return bean.getProjectId();
    }

    @Override
    protected void setValue(ProjectRole bean, Project value) {
        bean.setProject(value);
    }

    @Override
    protected Project getValue(Long key) {
        return projectRepository.findOne(key);
    }

    @Override
    protected Map<Long, Project> mgetValue(Collection<Long> keys) {
        return projectRepository.mget(keys);
    }
}
