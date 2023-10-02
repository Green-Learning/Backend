package com.greenLearning.greenlearning.repository;

import com.greenLearning.greenlearning.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

}
