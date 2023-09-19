package com.consumer.service;

import com.consumer.model.StudentModel;
import com.consumer.util.KafkaConstants;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerService {

    @KafkaListener(topics = KafkaConstants.TOPIC, groupId = KafkaConstants.GROUP_ID)  // containerFactory = "kafkaListenerContainerFactory")
    public void listener(List<StudentModel> studentList)
    {
        System.out.println("******Msg RECEIVED from Kafka Topic********");
        studentList.forEach(System.out::println);
    }

}
