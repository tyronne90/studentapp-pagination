package com.sgic.warapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.sgic.warapp.entity.Student;
import com.sgic.warapp.repo.StudentRepo;
import com.sgic.warapp.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  StudentRepo studentRepo;

  @Override
  public List<Student> getAllStudent() {
    return studentRepo.findAll();
  }

  @Override
  public List<Student> allStudents(Integer pageNo, Integer pageSize, String sortBy) {
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

    Page<Student> pagedResult = studentRepo.findAll(paging);

    if (pagedResult.hasContent()) {
      return pagedResult.getContent();
    } else {
      return new ArrayList<Student>();
    }
  }
  
  @Override
  public List<Student> findByAge(Integer pageNo, int age) {
    Integer pageSize = 2;
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("age").descending());
    
    Slice<Student> slicedResult = studentRepo.findStudentByAge(age, paging);
     
    List<Student> employeeList = slicedResult.getContent();
    return employeeList;
  }


  @Override
  public Page<Student> findAll(Pageable pageable) {
    return studentRepo.findAll(pageable);
  }

  @Override
  public Student saveStudent(Student student) {
    return studentRepo.save(student);
  }

  @Override
  public Student findOneStudent(Long id) {
    return studentRepo.findStudentById(id);
  }

  @Override
  public Object updateStudent(Student student) {
    if (student.getId() != null) {
      studentRepo.save(student);
    }
    return null;
  }

 


}
