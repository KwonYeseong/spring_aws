package com.yeseong.admin.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// User table을 access할 Repo
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // email을 통해 중복검사하기 위한 메소드
}
