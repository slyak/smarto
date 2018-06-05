package com.slyak.smarto.assembler;

import com.slyak.smarto.domain.Host;
import com.slyak.smarto.domain.ProjectGroupHost;
import com.slyak.smarto.repository.HostRepository;
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
public class ProjectGroupHostAssembler extends EntityAssemblerOne<ProjectGroupHost, Long, Host> {

    private final HostRepository hostRepository;

    @Autowired
    public ProjectGroupHostAssembler(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    @Override
    protected Long getKey(ProjectGroupHost bean) {
        return bean.getId().getHostId();
    }

    @Override
    protected void setValue(ProjectGroupHost bean, Host value) {
        bean.setHost(value);
    }

    @Override
    protected Host getValue(Long key) {
        return hostRepository.findOne(key);
    }

    @Override
    protected Map<Long, Host> mgetValue(Collection<Long> keys) {
        return hostRepository.mget(keys);
    }
}
