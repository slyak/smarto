package com.slyak.mirrors.assembler;

import com.slyak.mirrors.domain.ProjectGroupScript;
import com.slyak.mirrors.domain.Script;
import com.slyak.mirrors.repository.ScriptRepository;
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
public class ProjectGroupScriptAssembler extends EntityAssemblerOne<ProjectGroupScript, Long, Script> {

    private final ScriptRepository scriptRepository;

    @Autowired
    public ProjectGroupScriptAssembler(ScriptRepository scriptRepository) {
        this.scriptRepository = scriptRepository;
    }

    @Override
    protected Long getKey(ProjectGroupScript bean) {
        return bean.getScriptId();
    }

    @Override
    protected void setValue(ProjectGroupScript bean, Script value) {
        bean.setScript(value);
    }

    @Override
    protected Script getValue(Long key) {
        return scriptRepository.findOne(key);
    }

    @Override
    protected Map<Long, Script> mgetValue(Collection<Long> keys) {
        return scriptRepository.mget(keys);
    }
}
