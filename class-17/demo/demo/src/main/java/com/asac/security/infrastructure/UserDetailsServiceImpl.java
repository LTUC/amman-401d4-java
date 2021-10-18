package com.asac.security.infrastructure;

import com.asac.security.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = studentRepository.findStudentByUsername(username);

        if (person == null) {
            System.out.print("Username not found");
            throw new UsernameNotFoundException((username + " not found"));
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(person, null, person.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return person;
    }
}
