package com.sgic.warapp.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.sgic.warapp.entity.Student;

public interface StudentService {


  public List<Student> getAllStudent();

  public List<Student> allStudents(Integer pageNo, Integer pageSize, String sortBy);
  
  public List<Student> findByAge(Integer pageNo, int age);

  public Page<Student> findAll(Pageable pageable);

  public Student saveStudent(Student student);

  public Student findOneStudent(Long id);

  public Object updateStudent(Student student);

}
