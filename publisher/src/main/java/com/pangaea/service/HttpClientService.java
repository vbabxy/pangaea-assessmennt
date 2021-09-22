package com.pangaea.service;

import com.pangaea.model.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 9/22/21
 */
@Slf4j
@Service
public class HttpClientService {

    public WebResponse postHttpRequest(final String url, final String body, Map<String, String> headers) {
        try {
            log.debug("Visiting {} ", url);
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(ofJsonXmlForm(body))
                    .headers(convertMapToArray(headers))
                    .build();

            return getWebResponse(request);

        }catch (IOException | InterruptedException imi) {
            return WebResponse.builder()
                    .code(999)
                    .body(imi.getLocalizedMessage())
                    .build();
        }
    }


    private WebResponse getWebResponse(HttpRequest request) throws IOException, InterruptedException {
        var client = getHttpClient();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        log.info("Came back with Response code {} ", response.statusCode());

        return WebResponse.builder()
                .code(response.statusCode())
                .body(response.body())
                .headers(response.headers().map())
                .build();
    }

    private HttpClient getHttpClient() {
        return HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(60))
                .build();
    }

    private HttpRequest.BodyPublisher ofJsonXmlForm(String data) {
        return HttpRequest.BodyPublishers.ofString(data);
    }

    private String[] convertMapToArray(Map<String, String> headers) {
        if(headers.isEmpty()) return new String[]{"Content-Type","application/json"};

        List<String> headerArray = new ArrayList<>();
        for(String key : headers.keySet()) {
            headerArray.add(key);
            headerArray.add(headers.get(key));
        }

        return headerArray.toArray(new String[]{});
    }
}
