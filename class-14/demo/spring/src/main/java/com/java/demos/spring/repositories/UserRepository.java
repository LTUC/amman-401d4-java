package com.java.demos.spring.repositories;

import com.java.demos.spring.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findAppUserByEmail(String email);
    Optional<AppUser> findAppUsersByUsername(String username);
}
