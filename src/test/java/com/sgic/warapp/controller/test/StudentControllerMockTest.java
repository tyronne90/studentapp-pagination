package com.sgic.warapp.controller.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.sgic.warapp.controller.StudentController;
import com.sgic.warapp.entity.Student;
import com.sgic.warapp.service.impl.StudentServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerMockTest {

  @InjectMocks
  private StudentController controller;

  @Mock
  private StudentServiceImpl service;


  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void getAllStudents() {
    List<Student> studentList = new ArrayList<>();
    Student stOne = new Student("one", 1);
    Student sttwo = new Student("two", 2);
    studentList.add(stOne);
    studentList.add(sttwo);


    when(service.getAllStudent()).thenReturn(studentList);

    List<Student> stList = controller.getAllStudent();

    assertEquals(2, stList.size());
    verify(service, times(1)).getAllStudent();
  }

  @Test
  public void getStudentById() {
    when(service.findOneStudent(1L)).thenReturn(new Student("abc", 23));
    Student student = controller.getOneStudent(1L);

    assertEquals("abc", student.getName());
    assertEquals(23, student.getAge());

  }


  @Test
  public void saveStudent() {
    // Student student = mock(Student.class);
    Student student = new Student("Jim", 22);
    controller.saveStudent(student);
    verify(service, times(1)).saveStudent(student);
    assertEquals("Jim", student.getName());
    assertEquals(22, student.getAge());

  }



}
