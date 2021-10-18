package com.asac.security.web;

import com.asac.security.domain.Department;
import com.asac.security.domain.Role;
import com.asac.security.domain.Person;
import com.asac.security.infrastructure.DepartmentRepository;
import com.asac.security.infrastructure.RoleRepository;
import com.asac.security.infrastructure.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup";
    }

//    @PostAuthorize("hasAuthority('ADMIN')")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/grades")
    public String getGradesPage() {
        return "grades";
    }

    @GetMapping("/signin")
    public String getLoginPage() {
        return "signin";
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

    @GetMapping("/signup/users")
    public String getCreateAppUserPage(Model model) {
        List<Department> departments = departmentRepository.findAll();
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("departments", departments);
        model.addAttribute("roles", roles);
        return "create";
    }

    @PostMapping("/signup/users")
    public RedirectView createAppUser(@RequestParam String username,
                                      @RequestParam String password,
                                      @RequestParam Long department,
                                      @RequestParam Long role) { // this can be done as a model attribute
        Person newPerson = new Person(username, encoder.encode(password));
        newPerson.setDepartment(departmentRepository.findById(department).orElseThrow());
        Role foundRole = roleRepository.findById(role).orElseThrow();
        newPerson.setRole(foundRole);

        studentRepository.save(newPerson);

        return new RedirectView("/signin");
    }

    @PostMapping("/signup")
    public RedirectView attemptSignUp(@RequestParam String username, @RequestParam String password, @RequestParam Long department) {
        Person newPerson = new Person(username, encoder.encode(password));
        newPerson.setDepartment(departmentRepository.findById(department).orElseThrow());
        Role role = roleRepository.findRoleByName("ADMIN");
        newPerson.setRole(role);
        studentRepository.save(newPerson);

        return new RedirectView("/signin");
    }
}
