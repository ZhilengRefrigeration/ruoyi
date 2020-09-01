package com.ruoyi.common.mqtt.service;

import org.eclipse.paho.client.mqttv3.MqttException;

public class ss {
    public static void main(String[] args) throws MqttException {
        MqttService mqttConnect = new MqttService();
        mqttConnect.setMqttClient("admin", "public", new Callback());
        mqttConnect.pub("com/iot/init", "Mr.Qu" + (int) (Math.random() * 100000000));
        //mqttConnect.sub("com/iot/init");
    }
}
