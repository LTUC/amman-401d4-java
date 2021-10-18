package com.asac.security;

import com.asac.security.domain.Department;
import com.asac.security.domain.Role;
import com.asac.security.infrastructure.DepartmentRepository;
import com.asac.security.infrastructure.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SecurityApplication {
    private static final Logger log = LoggerFactory.getLogger(SecurityApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepository, DepartmentRepository departmentRepository) {
        return args -> {
            log.info("Preloading " + roleRepository.save(new Role("STUDENT")));
            log.info("Preloading " + roleRepository.save(new Role("TEACHER")));
            log.info("Preloading " + roleRepository.save(new Role("ADMIN")));

            log.info("Preloading " + departmentRepository.save(new Department("ASAC")));
            log.info("Preloading " + departmentRepository.save(new Department("HOSPITALITY")));
        };
    }
}
