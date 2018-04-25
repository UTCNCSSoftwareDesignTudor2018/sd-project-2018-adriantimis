package com.Project.SdProject.DataAccess.Repositories;

import com.Project.SdProject.DataAccess.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
