package com.sgic.warapp.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sgic.warapp.entity.Student;
import com.sgic.warapp.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {
  @Autowired
  private StudentService studentService;

  @GetMapping("/")
  public String home() {
    return "Hello,\nWelcome";
  }

  @PostMapping("/student")
  public Student saveStudent(@Valid @RequestBody Student student) {
    studentService.saveStudent(student);
    return studentService.findOneStudent(student.getId());
  }

  @GetMapping("/students")
  public List<Student> getAllStudent() {
    return studentService.getAllStudent();
  }

  // http://localhost:8080/studentapp/api/list?pageSize=2&pageNo=0&sortBy=id
  @GetMapping("/list")
  public List<Student> allStudent(@RequestParam(defaultValue = "0") Integer pageNo,
      @RequestParam(defaultValue = "5") Integer pageSize,
      @RequestParam(defaultValue = "id") String sortBy) {
    List<Student> list = studentService.allStudents(pageNo, pageSize, sortBy);
    return list;
  }

  // http://localhost:8080/studentapp/api/listage/23?pageNo=0
  @GetMapping("/listage/{age}")
  public List<Student> listallStudent(@PathVariable("age") int age,
      @RequestParam(defaultValue = "0") Integer pageNo) {
    List<Student> list = studentService.findByAge(pageNo, age);
    return list;
  }

  // http://localhost:8080/studentapp/api/listage/23/1
  @GetMapping("/listages/{age}/{page}")
  public List<Student> listallStudents(@PathVariable("age") int age,
      @PathVariable("page") Integer pageNo) {
    List<Student> list = studentService.findByAge(pageNo, age);
    return list;
  }


  // http://localhost:8080/studentapp/api/studentslist?page=0&size=2&sort=id
  @GetMapping("/studentslist")
  public Page<Student> findAll(Pageable pageable) {
    return studentService.findAll(pageable);
  }

  @GetMapping("/student/{id}")
  public Student getOneStudent(@PathVariable("id") Long id) {
    return studentService.findOneStudent(id);
  }

  @PutMapping("/student")
  public Object updateStudent(@Valid @RequestBody Student student) {
    return studentService.updateStudent(student);

  }

}
