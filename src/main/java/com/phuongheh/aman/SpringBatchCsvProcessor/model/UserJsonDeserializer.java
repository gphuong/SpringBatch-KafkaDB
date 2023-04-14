package com.phuongheh.aman.SpringBatchCsvProcessor.model;

import org.springframework.kafka.support.serializer.JsonDeserializer;

public class UserJsonDeserializer extends JsonDeserializer<User> {
    @Override
    public User deserialize(String topic, byte[] data) {
        try {
            return super.deserialize(topic, data);
        } catch (Exception ex) {
            System.out.println("Problem deserializing data " + new String(data) + " on topic " + topic);
            return null;
        }
    }
}
