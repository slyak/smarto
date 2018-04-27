package com.slyak.mirrors.web;

import com.slyak.mirrors.domain.GroupHost;
import com.slyak.mirrors.domain.GroupScript;
import com.slyak.mirrors.dto.ScriptMessage;
import com.slyak.mirrors.service.GroupScriptCallback;
import com.slyak.mirrors.service.MirrorManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * .
 *
 * @author stormning 2018/4/24
 * @since 1.3.0
 */
@Controller
@Slf4j
public class SSH2Controller {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MirrorManager mirrorManager;


    @MessageMapping("/ssh/exec")
    public void handle(Message<ScriptMessage> message) throws IOException {
        ScriptMessage payload = message.getPayload();
        mirrorManager.execGroupScripts(payload.getGroupId(), new GroupScriptCallback() {
            @Override
            public void processOut(GroupHost groupHost, GroupScript groupScript, String out) {
                messagingTemplate.convertAndSend("/ssh/logs/" + payload.getUser(), out);
            }

            @Override
            public void processError(GroupHost groupHost, GroupScript groupScript, String error) {
                messagingTemplate.convertAndSend("/ssh/logs/" + payload.getUser(), error);
            }
        });
    }
}
