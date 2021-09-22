package com.pangaea.service;

import com.pangaea.domain.RequestDto;
import com.pangaea.domain.ResponseDto;
import com.pangaea.listener.PubSubListenerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PublisherService {

    private final PubSubListenerService pubSubListenerService;

    public PublisherService(PubSubListenerService pubSubListenerService) {
        this.pubSubListenerService = pubSubListenerService;
    }

    public ResponseDto publish(RequestDto requestDto, String topic) {
        log.info("about to publish a new topic {} ", topic);
        pubSubListenerService.publish(topic, requestDto);

        return new ResponseDto(requestDto, topic);
    }
}
