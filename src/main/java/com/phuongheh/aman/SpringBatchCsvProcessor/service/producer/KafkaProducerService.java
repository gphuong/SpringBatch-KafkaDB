package com.phuongheh.aman.SpringBatchCsvProcessor.service.producer;

import com.phuongheh.aman.SpringBatchCsvProcessor.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Collection;

@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    @Value("${kafka.csv.topic}")
    private String topic;

    private static final Logger logger = Logger.getLogger(KafkaProducerService.class);

    public void publishUser(Collection<? extends User> users) {
        users.forEach(user -> {
            kafkaTemplate.send(topic, user).addCallback(new ListenableFutureCallback<SendResult<String, User>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    logger.info("Error publishing " + user);
                }

                @Override
                public void onSuccess(SendResult<String, User> stringUserSendResult) {
                    logger.info("Published successfully " + user + " to kafka " + stringUserSendResult.toString());
                }
            });
        });
    }
}
