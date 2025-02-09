package myform.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import myform.entity.StudentForm;

@Repository
public interface StudentFormRepository extends JpaRepository<StudentForm, Integer> {

}
