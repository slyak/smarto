package com.slyak.smarto.web;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.slyak.core.ssh2.StdEvent;
import com.slyak.smarto.dto.BatchHostLogResponse;
import com.slyak.smarto.dto.BatchQuery;
import com.slyak.smarto.dto.TaskLogRequest;
import com.slyak.smarto.service.SmartoManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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

    private final SmartoManager smartoManager;

    private Cache<String, TaskLogRequest> onlineRequests =
            CacheBuilder.newBuilder().expireAfterAccess(5, TimeUnit.MINUTES).build();


    @Autowired
    public LogController(
            SimpMessagingTemplate messagingTemplate,
            SmartoManager smartoManager
    ) throws IOException {
        this.messagingTemplate = messagingTemplate;
        this.smartoManager = smartoManager;
    }

    @MessageMapping("/ssh/logs")
    @SneakyThrows
    public void logs(Message<TaskLogRequest> message) throws IOException {
        TaskLogRequest taskLogRequest = message.getPayload();
        onlineRequests.put(taskLogRequest.getId(), taskLogRequest);

        String logFileName = smartoManager.getBatchLogfile(taskLogRequest.getBatchId(), taskLogRequest.getHostId());
        File logFile = new File(logFileName);
        while (!logFile.exists()) {
            Thread.sleep(1000);
        }
        LineIterator iterator = FileUtils.lineIterator(logFile);
        int lineCount = 0;
        while (iterator.hasNext()) {
            send(taskLogRequest.getId(), iterator.next(), ++lineCount);
        }
    }

    @EventListener(StdEvent.class)
    public void processStdEvent(StdEvent stdEvent) {
        if (onlineRequests.size() > 0) {
            Long batchId = stdEvent.getProperty("batchId");
            Long hostId = stdEvent.getProperty("hostId");
            Collection<TaskLogRequest> requests = onlineRequests.asMap().values();
            for (TaskLogRequest request : requests) {
                if (Objects.equals(request.getBatchId(), batchId) && Objects.equals(request.getHostId(), hostId)) {
                    send(request.getId(), stdEvent.getLine(), stdEvent.getNumber());
                }
            }
        }
    }

    private void send(String requestId, String log, int belongsToLine) {
        TaskLogRequest request = onlineRequests.getIfPresent(requestId);
        if (request != null) {
            int line = request.getLine();
            if (belongsToLine > line) {
                messagingTemplate.convertAndSend("/ssh/receive/" + requestId, new BatchHostLogResponse(log));
                request.setLine(belongsToLine);
            }
        }
    }

    @RequestMapping("/log/detail")
    public void detail(ModelMap modelMap) {
        modelMap.put("requestId", RandomStringUtils.randomAlphabetic(6));
    }

    @RequestMapping("/logs")
    public void logs(BatchQuery batchQuery, @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, ModelMap modelMap) {
        modelMap.put("page", smartoManager.queryBatches(batchQuery, pageable));
    }

}