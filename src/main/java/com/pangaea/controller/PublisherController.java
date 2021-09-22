package com.pangaea.controller;

import com.pangaea.domain.RequestDto;
import com.pangaea.service.PublisherService;
import com.pangaea.service.SubscriberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/publisher")
@Api(value = "/publisher", tags = "publisher")
public class PublisherController {


    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @ApiOperation(value = "Publisher Operation",notes = "Publisher Operation")
    @PutMapping(value = "/{topic}")
    public ResponseEntity<?> publisher(@PathVariable("topic") String topic, @RequestBody @Valid RequestDto requestDto) {
        return ResponseEntity.ok(publisherService.publish(requestDto, topic));
    }
}
