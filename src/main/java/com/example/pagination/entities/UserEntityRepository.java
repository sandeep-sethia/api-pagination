package com.example.pagination.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    List<UserEntity> findByFirstName(String firstName);

    List<UserEntity> findByLastNameLikeIgnoreCase(String lastName);

    long countDistinctByCreatedAtLessThan(LocalDateTime createdAt);

}