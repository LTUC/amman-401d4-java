package com.asac.security.web;

import com.asac.security.domain.Department;
import com.asac.security.domain.Student;
import com.asac.security.infrastructure.DepartmentRepository;
import com.asac.security.infrastructure.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/profile")
    public String getProfilePage(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("department", studentRepository.findStudentByUsername(userDetails.getUsername()).getDepartment());
        return "profile";
    }

    @GetMapping("/department")
    public String getDepartmentPage(Model model) {
        model.addAttribute("departments", departmentRepository.findAll());

        return "department";
    }

    @PostMapping("/department")
    public RedirectView addDepartment(@RequestParam String department) {
        departmentRepository.save(new Department(department));

        return new RedirectView("/department");
    }

    @PostMapping("/signup")
    public RedirectView attemptSignUp(@RequestParam String username, @RequestParam String password, @RequestParam Long department) {
        Student newStudent = new Student(username, encoder.encode(password));
        newStudent.setDepartment(departmentRepository.findById(department).orElseThrow());
        newStudent = studentRepository.save(newStudent);

        Authentication authentication = new UsernamePasswordAuthenticationToken(newStudent, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/");
    }
}
