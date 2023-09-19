package com.producer.controller;

import com.producer.model.StudentModel;
import com.producer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("student")
public class ProducerRestController {

    @Autowired
    private ProducerService producerService;

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addStudent(@RequestBody List<StudentModel> studentList)
    {
        studentList.forEach(System.out::println);

        this.producerService.saveStudents(studentList);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student List is stored !");
    }

}
