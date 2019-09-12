package com.sgic.warapp.controller.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgic.warapp.controller.StudentController;
import com.sgic.warapp.entity.Student;
import com.sgic.warapp.repo.StudentRepo;
import com.sgic.warapp.service.impl.StudentServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerApiTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private StudentServiceImpl service;

  @MockBean
  StudentRepo repo;

  @Test
  public void getAllStudent() throws Exception {
    List<Student> studentList = new ArrayList<>();
    Student stOne = new Student(1L, "name", 23);
    studentList.add(stOne);

    when(service.getAllStudent()).thenReturn(studentList);
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/students").accept(MediaType.APPLICATION_JSON);
    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    String expected = "[{'id':1,'name':'name','age':23}]";
    JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
  }

  @Test
  public void controllerTest() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/").accept(MediaType.APPLICATION_JSON);
    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    String expected = "Hello";
    assertEquals(expected, result.getResponse().getContentAsString());
  }

  @Test
  public void saveStudent() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post("/api/student")
            .content(asJsonString(new Student("jim", 22)))
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
        .andDo(print()).andExpect(status().isOk());
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void getOneStudent() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/api/student/{id}", 22).accept(MediaType.APPLICATION_JSON))
        .andDo(print()).andExpect(status().isOk());
  }

  @Test
  public void getAllStudents() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/students").accept(MediaType.APPLICATION_JSON))
        .andDo(print()).andExpect(status().isOk());
  }



}
