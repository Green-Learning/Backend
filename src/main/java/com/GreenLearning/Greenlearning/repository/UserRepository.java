package com.GreenLearning.Greenlearning.repository;

import com.GreenLearning.Greenlearning.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

}
