package com.slyak.smarto.assembler;

import com.slyak.smarto.domain.GlobalFile;
import com.slyak.smarto.domain.ScriptFile;
import com.slyak.smarto.repository.GlobalFileRepository;
import com.slyak.spring.jpa.EntityAssemblerOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * .
 *
 * @author stormning 2018/5/11
 * @since 1.3.0
 */
@Component
public class ScriptFileAssembler extends EntityAssemblerOne<ScriptFile, String, GlobalFile> {
    @Autowired
    private GlobalFileRepository globalFileRepository;

    @Override
    protected String getKey(ScriptFile bean) {
        return bean.getGlobalFileId();
    }

    @Override
    protected void setValue(ScriptFile bean, GlobalFile value) {
        bean.setGlobalFile(value);
    }

    @Override
    protected GlobalFile getValue(String key) {
        return globalFileRepository.findOne(key);
    }

    @Override
    protected Map<String, GlobalFile> mgetValue(Collection<String> keys) {
        return globalFileRepository.mget(keys);
    }
}
