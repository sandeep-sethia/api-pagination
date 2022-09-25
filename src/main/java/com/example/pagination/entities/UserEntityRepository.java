package com.example.pagination.entities;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserEntityRepository extends PagingAndSortingRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    Slice<UserEntity> findByFirstNameStartsWithIgnoreCase(String firstName, Pageable pageable);

    List<UserEntity> findByFirstName(String firstName);

    List<UserEntity> findByLastNameLikeIgnoreCase(String lastName);

    List<UserEntity> findByFirstNameLikeIgnoreCase(String lastName);

    long countDistinctByCreatedAtLessThan(LocalDateTime createdAt);

}