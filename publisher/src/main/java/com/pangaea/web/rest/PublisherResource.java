package com.pangaea.web.rest;

import com.pangaea.model.SubscriberRequest;
import com.pangaea.model.SubscriberResponse;
import com.pangaea.service.PublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/")
@RestController
public class PublisherResource {

    private final PublisherService publisherService;

    public PublisherResource(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping("subscribe/{topic}")
    public ResponseEntity<?> subscribe(@Valid @RequestBody SubscriberRequest subscriberRequest,
                                       @PathVariable String topic) {
        SubscriberResponse response = publisherService.registerSubscriber(topic, subscriberRequest.getUrl());
        return ResponseEntity.ok(response);
    }

    @PostMapping("publish/{topic}")
    public ResponseEntity<?> publish(@Valid @RequestBody String data, @PathVariable String topic) {
        publisherService.publish(topic, data);
        return ResponseEntity.ok(null);
    }
}
