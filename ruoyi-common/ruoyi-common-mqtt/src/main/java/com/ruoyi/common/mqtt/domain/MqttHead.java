package com.ruoyi.common.mqtt.domain;

import java.io.Serializable;

public class MqttHead implements Serializable {

    private int type;
    private String topic;
    private MqttBody body;
}
