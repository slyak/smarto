package com.slyak.mirrors.converter;

import com.slyak.mirrors.domain.OS;
import com.slyak.mirrors.repository.OSRepository;
import com.slyak.spring.jpa.converter.AbstractConverter;
import com.slyak.web.ui.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * .
 *
 * @author stormning 2018/5/14
 * @since 1.3.0
 */
@Component
public class OsVersionsOptionConverter extends AbstractConverter<OS, Option, Long> {

    @Autowired
    private OSRepository osRepository;

    @Override
    protected Long getId(OS source) {
        return source.getId();
    }

    @Override
    protected Option internalConvert(OS source) {
        Option option = new Option();
        option.setTitle(source.getOs());
        option.setValue(source.getOs());
        return option;
    }

    @Override
    protected OS internalGet(Long aLong) {
        return osRepository.findOne(aLong);
    }

    @Override
    protected Map<Long, OS> internalMGet(Collection<Long> longs) {
        return osRepository.mget(longs);
    }
}
