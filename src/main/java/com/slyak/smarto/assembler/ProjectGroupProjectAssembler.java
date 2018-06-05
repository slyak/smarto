package com.slyak.smarto.assembler;

import com.slyak.smarto.domain.Project;
import com.slyak.smarto.domain.ProjectGroup;
import com.slyak.smarto.repository.ProjectRepository;
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
public class ProjectGroupProjectAssembler extends EntityAssemblerOne<ProjectGroup, Long, Project> {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectGroupProjectAssembler(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    protected Long getKey(ProjectGroup bean) {
        return bean.getProjectId();
    }

    @Override
    protected void setValue(ProjectGroup bean, Project value) {
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
