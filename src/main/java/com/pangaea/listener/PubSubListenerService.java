package com.pangaea.listener;

import com.pangaea.domain.RequestDto;
import com.pangaea.domain.ResponseDto;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class PubSubListenerService {

    private static ConcurrentMap<String, Object> concurrentMap = null;

    public static ConcurrentMap getConcurrentMap() {
        if(concurrentMap == null || concurrentMap.isEmpty()) {
            concurrentMap = new ConcurrentHashMap<>();
        }
        return concurrentMap;
    }

    public void publish(String topic, RequestDto request) {

        getConcurrentMap().putIfAbsent(topic, request);
    }

    public ResponseDto subscribe(String topic) {
        RequestDto request = (RequestDto) getConcurrentMap().get(topic);

        if(request == null) {
            return ResponseDto.builder()
                    .data(" Topic : "+topic + " Not Found.")
                    .topic(topic)
                    .build();
        }
        ResponseDto responseDto = new ResponseDto(request, topic);
        getConcurrentMap().remove(topic);
        return responseDto;
    }
}
