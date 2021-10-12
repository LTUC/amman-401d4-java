package com.java.demos.spring.repositories;

import com.java.demos.spring.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findAuthorByEmail(String email);
    Optional<Author> findAuthorByUsername(String username);
}
