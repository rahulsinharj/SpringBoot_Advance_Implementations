package com.producer;

import com.producer.model.StudentModel;
import com.producer.service.ProducerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class KafkaProducerSendingListApplicationTests {

	@Autowired
	private ProducerService producerService;

	@Test
	void sendStudentList() {

		StudentModel s1 = new StudentModel(131, "Rahul", "Delhi");
		StudentModel s2 = new StudentModel(132, "Vikas", "Patna");
		StudentModel s3 = new StudentModel(133, "Shubham", "Noida");

		List<StudentModel> studentList = new ArrayList<>();
		studentList.add(s1);
		studentList.add(s2);
		studentList.add(s3);

		this.producerService.saveStudents(studentList);


	}
}
