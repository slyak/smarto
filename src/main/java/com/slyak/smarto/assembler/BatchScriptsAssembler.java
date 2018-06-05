package com.slyak.smarto.assembler;

import com.slyak.smarto.domain.Batch;
import com.slyak.smarto.domain.Script;
import com.slyak.smarto.repository.ScriptRepository;
import com.slyak.spring.jpa.EntityAssemblerMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * .
 *
 * @author stormning 2018/5/21
 * @since 1.3.0
 */
@Component
public class BatchScriptsAssembler extends EntityAssemblerMany<Batch, Long, Script> {
    @Autowired
    private ScriptRepository scriptRepository;

    @Override
    protected void setValue(Batch bean, List<Script> value) {
        bean.setScripts(value);
    }

    @Override
    protected List<Script> getValue(List<Long> keys) {
        return scriptRepository.findAll(keys);
    }

    @Override
    protected List<Long> getKeys(Batch bean) {
        return bean.getScriptIds();
    }

    @Override
    protected Map<Long, Script> mgetValue(Collection<Long> keys) {
        return scriptRepository.mget(keys);
    }
}
