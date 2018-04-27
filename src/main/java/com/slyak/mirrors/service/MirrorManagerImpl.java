package com.slyak.mirrors.service;

import com.slyak.core.util.SSH2;
import com.slyak.core.util.StdCallback;
import com.slyak.mirrors.domain.Group;
import com.slyak.mirrors.domain.GroupHost;
import com.slyak.mirrors.domain.GroupScript;
import com.slyak.mirrors.domain.Project;
import com.slyak.mirrors.repository.GroupHostRepository;
import com.slyak.mirrors.repository.GroupRepository;
import com.slyak.mirrors.repository.ProjectRepository;
import com.slyak.mirrors.repository.ScriptRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * .
 *
 * @author stormning 2018/4/17
 * @since 1.3.0
 */
@Service
@Slf4j
public class MirrorManagerImpl implements MirrorManager {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ScriptRepository scriptRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupHostRepository groupHostRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public Page<Project> queryProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public List<GroupScript> getGroupScripts(Long groupId) {
        return null;
    }

    @Override
    public Group findGroup(Long groupId) {
        return groupRepository.findOne(groupId);
    }

    @Override
    public List<GroupHost> findGroupHosts(Long groupId) {
        return groupHostRepository.findByGroupId(groupId);
    }

    @Override
    public void execGroupScripts(Long groupId, GroupScriptCallback callback) {
        List<GroupHost> groupHosts = findGroupHosts(groupId);
        List<GroupScript> groupScripts = getGroupScripts(groupId);
        for (GroupHost groupHost : groupHosts) {
            SSH2 ssh2 = SSH2.connect(groupHost.getHost(), groupHost.getSshPort()).auth(groupHost.getUser(), groupHost.getPassword());
            for (GroupScript groupScript : groupScripts) {
                execCommand(ssh2, "sh -c " + groupScript.getStorePath(), new StdCallback() {
                    @Override
                    public void processOut(String out) {
                        callback.processOut(groupHost, groupScript, out);
                    }

                    @Override
                    public void processError(String error) {
                        callback.processError(groupHost, groupScript, error);
                    }
                });
            }
        }
    }

    private void execCommand(SSH2 ssh2, String command, StdCallback callback) {
        try {
            ssh2.execCommand(command, callback);
        } finally {
            log.info("closing ssh connection");
            ssh2.disconnect();
        }
    }

}