package com.slyak.mirrors.web;

import com.slyak.mirrors.dto.BatchHostLog;
import com.slyak.mirrors.dto.BatchHostLogResponse;
import com.slyak.mirrors.dto.BatchQuery;
import com.slyak.mirrors.service.MirrorManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;

/**
 * .
 *
 * @author stormning 2018/4/24
 * @since 1.3.0
 */
@Controller
@Slf4j
public class LogController {

    private final SimpMessagingTemplate messagingTemplate;

    private final MirrorManager mirrorManager;

    @Autowired
    public LogController(SimpMessagingTemplate messagingTemplate, MirrorManager mirrorManager) throws IOException {
        this.messagingTemplate = messagingTemplate;
        this.mirrorManager = mirrorManager;
    }

    @MessageMapping("/ssh/logs")
    public void logs(Message<BatchHostLog> message) throws IOException {
        BatchHostLog batchHostLog = message.getPayload();
        String logfile = mirrorManager.getBatchLogfile(batchHostLog.getBatchId(), batchHostLog.getHostId());
        LineIterator iterator = FileUtils.lineIterator(new File(logfile));
        while (iterator.hasNext()) {
            messagingTemplate.convertAndSend("/ssh/receive/" + batchHostLog.getLogKey(),
                    new BatchHostLogResponse(iterator.next()));
        }
    }

    @RequestMapping("/log/detail")
    public void detail() {
    }

    @RequestMapping("/logs")
    public void logs(BatchQuery batchQuery, Pageable pageable, ModelMap modelMap) {
        modelMap.put("page", mirrorManager.queryBatches(batchQuery, pageable));
    }

}
