package com.leonardo.desafio.picpay.service;

import com.leonardo.desafio.picpay.infra.GlobalErrorHandling;
import com.leonardo.desafio.picpay.model.Transaction;
import com.leonardo.desafio.picpay.model.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class NotificationService {
    private final RestClient restClient;
    private final KafkaTemplate<String, Transaction> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

    public NotificationService(RestClient.Builder builder, KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.restClient = builder.baseUrl(
                        "https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6")
                .build();;
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendNotification(Transaction transaction) {
        kafkaTemplate.send("transaction-notification", transaction);
    }
    @KafkaListener(topics = "transaction-notification", groupId = "picpay-desafio-backend")
    public void receiveNotification(Transaction transaction) {
        LOGGER.info("notifying transaction {}...", transaction);

        var response = restClient.get().retrieve().toEntity(Notification.class);

        if (response.getStatusCode().isError() || !response.getBody().message())
            throw new GlobalErrorHandling.NotificationException("Error notifying transaction " + transaction);

        LOGGER.info("notification has been sent {}...", response.getBody());
    }
    public void notify(Transaction transaction) {
        LOGGER.info("notifying transaction {}...", transaction);

        this.sendNotification(transaction);
    }
}
