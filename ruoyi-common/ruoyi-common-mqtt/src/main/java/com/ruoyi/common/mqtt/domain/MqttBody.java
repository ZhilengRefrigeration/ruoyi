package com.ruoyi.common.mqtt.domain;

import java.io.Serializable;

public class MqttBody implements Serializable {
    private int code;
    private String msg;
    private Object body;
}
