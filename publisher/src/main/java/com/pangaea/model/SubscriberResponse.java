package com.pangaea.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 9/22/21
 */
@Data
@Builder
public class SubscriberResponse implements Serializable {
    private String url;
    private String topic;
}
