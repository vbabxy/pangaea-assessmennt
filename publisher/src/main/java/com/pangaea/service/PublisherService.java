package com.pangaea.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pangaea.model.NotificationDto;
import com.pangaea.model.SubscriberResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class PublisherService {

    private final Map<String, List<String>> topicQueue;
    private final HttpClientService httpClientService;
    private final Gson gson;

    public PublisherService(HttpClientService httpClientService) {
        this.topicQueue = new ConcurrentHashMap<>();
        this.httpClientService = httpClientService;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    private List<String> getSubscriberByTopic(String topic) {
        List<String> subscriberList = topicQueue.get(topic);
        return subscriberList == null ? new ArrayList<>() : subscriberList;
    }

    public SubscriberResponse registerSubscriber(String topic, String url) {
        log.info("Registering {} with topic {}", url, topic);
        List<String> subscriberList = getSubscriberByTopic(topic);
        subscriberList.add(url);
        topicQueue.putIfAbsent(topic, subscriberList);
        return SubscriberResponse.builder()
                .topic(topic)
                .url(url)
                .build();
    }

    public void publish(String topic, String message) {
        List<String> subscriberUrls = getSubscriberByTopic(topic);
        if(subscriberUrls.isEmpty()) {
            log.error("Topic has no subscribers registered");
            return;
        }
        log.info("publishing to {} subscribers", subscriberUrls.size());
        subscriberUrls.forEach(url -> {
            NotificationDto notification = NotificationDto.builder()
                    .topic(topic)
                    .data(message)
                    .build();
            httpClientService.postHttpRequest(url, gson.toJson(notification), new HashMap<>());
        });
        log.info("Finished publishing to {} subscribers", subscriberUrls.size());
    }


}
