# Labs 16 and 17: CodeFellowship Login, Profiles & Posts

## Overview
For your remaining labs before the midterm project, you'll create an app called CodeFellowship that allows people learning to code to connect with each other and support each other on their coding journeys.

**Note** that this lab is meant to take up two lab sessions; this is a large number of feature tasks, and you should ensure you're making progress on the first day to ensure success on the second day.

## Setup
Create a new repo `codefellowship` to hold your work for the last 5 Spring labs. Use the Spring Initializr to set up an app with dependencies on Web, Thymeleaf, JPA, Postgres, and Security (and optionally DevTools for auto refresh of app on building). Remember to do your initial commit on the master branch before creating your feature branch. Also, see the below note about configuring Spring Security.

## Feature Tasks
Build an app that allows users to create their profile on `CodeFellowship`.

- The site should have a splash page at the root route (`/`) that contains basic information about the site, as well as a link to the "sign up" page.
- An `ApplicationUser` should have a `username`, `password` (will be hashed using BCrypt), `firstName`, `lastName`, `dateOfBirth`, `bio`, and any other fields you think are useful.
- The site should allow users to create an `ApplicationUser` on the "sign up" page.
    - Your Controller should have an `@Autowired private PasswordEncoder passwordEncoder; ` and use that to run `passwordEncoder.encode(password)` before saving the password into the new user.
- The site should have a page which allows viewing the data about a single `ApplicationUser`, at a route like `/users/{id}`.
    - This should include a default profile picture, which is the same for every user, and their basic information.
- Using the above cheat sheet, add the ability for users to log in to your app.
- When a user is logged in, the app should display the user's username on every page (probably in the heading).
- Ensure that your homepage, login, and registration routes are accessible to non-logged in users. All other routes should be limited to logged-in users.
- The site should be well-styled and attractive.
- The site should use reusable templates for its information. (At a minimum, it should have one Thymeleaf fragment that is used on multiple pages.)
- The site should have a non-whitelabel error handling page that lets the user know, at minimum, the error code and a brief message about what went wrong.
- Ensure that user registration also logs users into your app automatically.
- Add a `Post` entity to your app.
    - A `Post` has a `body` and a `createdAt` timestamp.
    - A logged-in user should be able to create a `Post`, and a post should belong to the user that created it.
        - hint: this is a relationship between two pieces of data
- A user's posts should be visible on their profile page.

## Stretch Goals
- A logged-in user should be able to edit their profile. (Double stretch: allow them to change their passwords if they enter their current password.)
- An "admin" user should be able to edit anyone's profile.

## IMPORTANT: A basic WebSecurityConfig file

To correctly configure Spring Security, please copy-paste this entire code snippet into a file called `WebSecurityConfig.java`:

```java
// TODO: put your package name here

import com.mycode.securedemo.appuser.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
            .cors().disable()
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/signup").permitAll()
                .anyRequest().permitAll()
            .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
            .and()
                .logout();
    }
}
```

## Stretch Goals
- Allow a logged-in user to edit their profile.

## Submission Instructions
* Work on a non-master branch and make commits appropriately.
* Ensure that you can run a single Gradle command to launch your server. Include that command, as well as a description of the project and the state of each of the feature tasks, in a README.md file.
* Create a pull request to your master branch with your work for this lab.
* Submit the link to that pull request on Canvas. Add a comment with the amount of time you spent on this assignment.

## Grading Rubric

- 1pt / README
- 1pt / Controllers and routes
- 1pt / Proper password hashing
- 2pt / Login
- 1pt / User page w/image and basic info, using Thymeleaf appropriately
