package com.greenLearning.greenlearning.repository;

import com.greenLearning.greenlearning.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserDetails findByUsername(String username);

}
