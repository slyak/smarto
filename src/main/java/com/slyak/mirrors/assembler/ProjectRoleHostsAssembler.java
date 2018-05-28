package com.slyak.mirrors.assembler;

import com.slyak.mirrors.domain.Host;
import com.slyak.mirrors.domain.ProjectRole;
import com.slyak.mirrors.domain.ProjectRoleHost;
import com.slyak.mirrors.repository.HostRepository;
import com.slyak.mirrors.repository.ProjectRoleHostRepository;
import com.slyak.spring.jpa.EntityAssemblerMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * .
 *
 * @author stormning 2018/5/28
 * @since 1.3.0
 */
@Component
public class ProjectRoleHostsAssembler extends EntityAssemblerMany<ProjectRole, Long, Host> {

    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private ProjectRoleHostRepository roleHostRepository;

    @Override
    protected void setValue(ProjectRole bean, List<Host> value) {
        bean.setHosts(value);
    }

    @Override
    protected List<Host> getValue(List<Long> keys) {
        return hostRepository.findAll(keys);
    }

    @Override
    protected List<Long> getKeys(ProjectRole bean) {
        List<ProjectRoleHost> hosts = roleHostRepository.findByRoleId(bean.getId());
        return hosts.stream().map(AbstractPersistable::getId).collect(Collectors.toList());
    }

    @Override
    protected Map<Long, Host> mgetValue(Collection<Long> keys) {
        return hostRepository.mget(keys);
    }
}
