package com.Project.SdProject.DataAccess.Repositories;

import com.Project.SdProject.DataAccess.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByUser_Username(String username);

}
