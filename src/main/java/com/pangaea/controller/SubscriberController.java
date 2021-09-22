package com.pangaea.controller;

import com.pangaea.service.SubscriberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/subscribe")
@Api(value = "/subscriber", tags = "subscriber")
public class SubscriberController {

    private final SubscriberService subscriberService;


    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @ApiOperation(value = "Subscriber Operation",notes = "Subscriber Operation")
    @GetMapping(value = "/{topic}")
    public ResponseEntity<?> subscriber(@PathVariable("topic") String topic) {
        return ResponseEntity.ok(subscriberService.subscribe(topic));
    }
}
