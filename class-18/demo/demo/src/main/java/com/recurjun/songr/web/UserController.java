package com.recurjun.songr.web;

import com.recurjun.songr.data.Album;
import com.recurjun.songr.data.User;
import com.recurjun.songr.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    String showUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());

        return "users";
    }

    @GetMapping("/users/hack")
    String getUser(@RequestParam String name, Model model) {
        model.addAttribute("users", userRepository.findUserByName(name));

        return "users";

    }

    @PostMapping("/users")
    RedirectView saveUser(@RequestParam String name) {

        userRepository.save(new User(name));

        return new RedirectView("/users");
    }

    @GetMapping("/follow/{id}")
    String showFollowSuccessScreen(@PathVariable("id") long id, Model model) {

        // user to follow
        User usertofollow = userRepository.findById(id).orElseThrow();

        // get current logged in user username

        // use the user name to find the user by username

        // once you have that object

        // add the curetn logged in user to the following of usertofollow

        // add usertofollow to current logged in user followers




        return "success";
    }
}
