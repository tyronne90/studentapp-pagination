package com.sgic.warapp.repo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sgic.warapp.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {
  public Student findStudentById(Long id);

  public Page<Student> findAll(Pageable pageable);
  
  public Slice<Student> findStudentByAge(int age, Pageable pageable);
}
