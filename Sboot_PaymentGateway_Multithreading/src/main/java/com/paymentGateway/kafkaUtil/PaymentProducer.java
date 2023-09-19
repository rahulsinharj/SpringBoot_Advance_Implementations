package com.paymentGateway.kafkaUtil;

import com.paymentGateway.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentProducer {
    @Autowired
    private KafkaTemplate<String, Payment> kafkaTemplate;

    public void sendPaymentRequest(Payment paymentRequest) {
        if (paymentRequest != null) {
            kafkaTemplate.send(KafkaConstants.TOPIC, paymentRequest);

            System.out.println("******Msg PUBLISHED to Kafka topic : " + paymentRequest.toString());
        }
    }
}
