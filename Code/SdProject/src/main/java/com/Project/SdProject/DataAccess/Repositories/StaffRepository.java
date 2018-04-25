package com.Project.SdProject.DataAccess.Repositories;

import com.Project.SdProject.DataAccess.Entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
}
