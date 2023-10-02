package com.greenlearning.greenlearning.repository;

import com.greenlearning.greenlearning.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

}
