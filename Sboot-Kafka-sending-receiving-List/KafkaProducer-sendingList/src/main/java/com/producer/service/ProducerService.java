package com.producer.service;

import com.producer.model.StudentModel;
import com.producer.util.KafkaConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProducerService {

    @Autowired
    private KafkaTemplate<String, List<StudentModel>> kafkaTemplate;

    public void saveStudents(List<StudentModel> studentList)
    {
        if(!studentList.isEmpty())
        {
//            kafkaTemplate.send(KafkaConstants.TOPIC, UUID.randomUUID().toString(),studentList);
            kafkaTemplate.send(KafkaConstants.TOPIC, studentList);

            System.out.println("************Msg PUBLISHED to Kafka topic***************");
        }
    }

}
