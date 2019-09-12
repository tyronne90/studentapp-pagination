package com.sgic.warapp.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(schema = "student_db", name = "studentdetails")
public class Student implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private int age;



  public Student() {
    super();
  }

  public Student(String name, int age) {
    super();
    this.name = name;
    this.age = age;
  }

  public Student(Long id, String name, int age) {
    super();
    this.id = id;
    this.name = name;
    this.age = age;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }


}
