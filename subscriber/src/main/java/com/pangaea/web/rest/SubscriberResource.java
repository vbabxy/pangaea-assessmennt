package com.pangaea.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@Slf4j
@RequestMapping("/")
@RestController
public class SubscriberResource {

    @PostMapping("test1")
    public ResponseEntity<?> acceptMessage1(@RequestBody String body) {
        log.info("[/test1] Received message {} @[{}]", body, ZonedDateTime.now());
        return ResponseEntity.ok(null);
    }

    @PostMapping("test2")
    public ResponseEntity<?> acceptMessage2(@RequestBody String body) {
        log.info("[/test2] Received message {} @[{}]", body, ZonedDateTime.now());
        return ResponseEntity.ok(null);
    }


}
