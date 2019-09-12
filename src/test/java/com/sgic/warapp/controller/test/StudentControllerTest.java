package com.sgic.warapp.controller.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.sgic.warapp.controller.StudentController;
import com.sgic.warapp.entity.Student;
import com.sgic.warapp.repo.StudentRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentControllerTest {

  @Autowired
  private StudentController service;

  @MockBean
  private StudentRepo repo;

  private static final Long ID = 1L;

  @Test
  public void getAllStudentTest() {
    when(repo.findAll())
        .thenReturn(Stream.of(new Student(1L, "Name", 12)).collect(Collectors.toList()));
    assertEquals(1, service.getAllStudent().size());
  }


  @Test
  public void updateTest() {
    Student student = mock(Student.class);
    when(student.getId()).thenReturn(ID);
    when(student.getName()).thenReturn("name");
    when(student.getAge()).thenReturn(23);
    when(repo.findStudentById(ID)).thenReturn(student);
    service.updateStudent(student);
    verify(repo).save(student);
  }

  @Test
  public void saveTest() {
    Student student = mock(Student.class);
    when(student.getId()).thenReturn(ID);
    when(student.getName()).thenReturn("name");
    when(student.getAge()).thenReturn(23);
    when(repo.findStudentById(ID)).thenReturn(student);
    service.saveStudent(student);
    verify(repo).save(student);

  }



}
