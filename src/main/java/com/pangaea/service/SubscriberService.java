package com.pangaea.service;

import com.pangaea.domain.ResponseDto;
import com.pangaea.listener.PubSubListenerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SubscriberService {

    private final PubSubListenerService pubSubListenerService;


    public SubscriberService(PubSubListenerService pubSubListenerService) {
        this.pubSubListenerService = pubSubListenerService;
    }

    public ResponseDto subscribe(String topic) {
        log.info("about to subscribe to topic {} ", topic);
        return pubSubListenerService.subscribe(topic);
    }
}
