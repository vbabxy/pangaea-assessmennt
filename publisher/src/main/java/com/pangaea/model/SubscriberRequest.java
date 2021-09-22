package com.pangaea.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 9/22/21
 */
@Data
public class SubscriberRequest implements Serializable {
    @NotBlank(message = "Subscriber url missing")
    private String url;
}
