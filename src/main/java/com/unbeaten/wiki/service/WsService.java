package com.unbeaten.wiki.service;

import com.unbeaten.wiki.domain.Doc;
import com.unbeaten.wiki.websocket.WebSocketServer;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WsService {
    @Resource
    private WebSocketServer webSocketServe;

    @Async
    public void sendInfo(String message, String logId) {
        MDC.put("LOG_ID",logId);
        webSocketServe.sendInfo(message);
    }
}
