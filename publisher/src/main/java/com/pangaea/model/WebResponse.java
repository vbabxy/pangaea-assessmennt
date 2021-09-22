package com.pangaea.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class WebResponse implements Serializable {
    private int code;
    private String body;
    private Map<String, List<String>> headers;
}
