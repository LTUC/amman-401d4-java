package com.asac.security.infrastructure;

import com.asac.security.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Person, Long> {
    Person findStudentByUsername(String username);
}
