package com.phuongheh.aman.SpringBatchCsvProcessor.service.consumer;

import com.phuongheh.aman.SpringBatchCsvProcessor.model.User;
import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private static Logger logger = Logger.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "${kafka.csv.topic}", containerFactory = "kafkaListenerContainerFactory", groupId = ("${kafka.csv.group.id"))
    public void consumer(User user) {
        logger.info("Consuming new message: " + user.getName() + " -> " + user);
    }
}
