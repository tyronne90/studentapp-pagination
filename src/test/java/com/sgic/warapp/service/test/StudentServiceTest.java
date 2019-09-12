package com.sgic.warapp.service.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.sgic.warapp.entity.Student;
import com.sgic.warapp.repo.StudentRepo;
import com.sgic.warapp.service.impl.StudentServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class StudentServiceTest {

  @Mock
  private StudentRepo mockRepo;

  @InjectMocks
  private StudentServiceImpl serviceImpl;

  private static final Long ID = 1L;

  @Test
  public void getAllStudentsTest() {
    serviceImpl.getAllStudent();
    verify(mockRepo).findAll();
  }

  @Test
  public void saveStudentTest() {
    Student student = mock(Student.class);
    serviceImpl.saveStudent(student);
    verify(mockRepo).save(student);
  }

  @Test
  public void getOneStudentTest() {
    serviceImpl.findOneStudent(ID);
    verify(mockRepo).findStudentById(ID);
  }

  @Test
  public void updateStudentTest() {
    Student student = mock(Student.class);
    serviceImpl.updateStudent(student);
    verify(mockRepo).save(student);
  }



}
