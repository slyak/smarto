package com.slyak.smarto.assembler;

import com.slyak.smarto.domain.Batch;
import com.slyak.smarto.domain.Host;
import com.slyak.smarto.repository.HostRepository;
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
public class BatchHostsAssembler extends EntityAssemblerMany<Batch, Long, Host> {

    @Autowired
    private HostRepository hostRepository;

    @Override
    protected void setValue(Batch bean, List<Host> value) {
        bean.setHosts(value);
    }

    @Override
    protected List<Host> getValue(List<Long> keys) {
        return hostRepository.findAll(keys);
    }

    @Override
    protected List<Long> getKeys(Batch bean) {
        return bean.getHostIds();
    }

    @Override
    protected Map<Long, Host> mgetValue(Collection<Long> keys) {
        return hostRepository.mget(keys);
    }
}
