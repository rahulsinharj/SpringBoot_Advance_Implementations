package myform.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myform.entity.StudentForm;

@Service
public class StudentFormDao {

	@Autowired
	private StudentFormRepository studentFormRepository;
	
	public StudentForm saveStudentForm(StudentForm stuform)
	{
		this.studentFormRepository.save(stuform);
		return studentFormRepository.findById(stuform.getSid()).get();
	}
	
	public List<StudentForm> getAllStudentsList()
	{
		return (List<StudentForm>)studentFormRepository.findAll();
	}
}
