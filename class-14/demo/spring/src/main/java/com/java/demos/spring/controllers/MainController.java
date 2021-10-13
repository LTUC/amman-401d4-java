package com.java.demos.spring.controllers;

import com.java.demos.spring.models.AppUser;
import com.java.demos.spring.models.Post;
import com.java.demos.spring.models.dto.PostDTO;
import com.java.demos.spring.repositories.UserRepository;
import com.java.demos.spring.repositories.PostRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController {

//    @Autowired
//    private BCrypt encoder;

    // field injection
    @Autowired
    private PostRepository postRepository;

    // this way is better
    // constructor injection
    private final UserRepository userRepository;
    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ResponseBody
    @PostMapping("/v1/posts")
    public Post createNewPost(@RequestBody Post post) { // when working with json data
        return postRepository.save(post);
    }

    @GetMapping("/v2/posts")
    public String getBlogPosts(Model model, HttpServletRequest request) {
        model.addAttribute("posts", postRepository.findAll());

        String username = (String) request.getSession().getAttribute("username");

        model.addAttribute("username", username);
        return "posts";
    }

    @PostMapping("/v2/posts")
    public RedirectView createNewBlogPost(@ModelAttribute PostDTO postDTO) { // modelattribute when working with fomr data
        AppUser author = userRepository.findAppUsersByUsername(postDTO.getUser()).orElseThrow();
        Post newPost = new Post(author, postDTO.getContent());
        postRepository.save(newPost);

        return new RedirectView("posts");
    }

    @GetMapping("/v2/posts/users/{username}")
    public String findPostByUsername(@PathVariable String username, Model model) {
        List<Post> posts = postRepository.findAllByAppUser_Username(username);
        model.addAttribute("authorPost", posts);

        return "post";
    }

    @GetMapping("/v2/posts/{postId}")
    public String findPostByPostId(@PathVariable String postId, Model model) {
        Post post = postRepository.findById(Long.parseLong(postId)).orElseThrow();
        model.addAttribute("authorPost", post);

        return "post";
    }

    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup";
    }

    @PostMapping("/signup/users")
    public RedirectView signUpNewUser(@ModelAttribute AppUser appUser) {
        appUser.setPassword(BCrypt.hashpw(appUser.getPassword(), BCrypt.gensalt())); // we have encrypted the user password
        userRepository.save(appUser);

        // we should then show the post creation page
        return new RedirectView("/signin/users");
    }

    @GetMapping("/signin/users")
    public String getSignInPage() {
        return "signin";
    }

    @PostMapping("/signin/users")
    public RedirectView signInUser(HttpServletRequest request, @ModelAttribute AppUser appUser, RedirectAttributes attributes) {
        AppUser foundUser = userRepository.findAppUsersByUsername(appUser.getUsername()).orElseThrow();
        String savedUserPassword = foundUser.getPassword();

        if (BCrypt.checkpw(appUser.getPassword(), savedUserPassword)) { // successfully sign in
            // store session data - in this case the username
            HttpSession session = request.getSession();
            session.setAttribute("username", foundUser.getUsername());

            return new RedirectView("/v2/posts");
        } else {
            RedirectView redirectView = new RedirectView("/failedSignIn", true);
//            attributes.addAttribute("username", foundUser.getUsername());
            return redirectView;
        }
    }

    @GetMapping("/failedSignIn")
    public String getSignInErrorPage() {
        return "failedSignIn";
    }

    @GetMapping("/sessiondata")
    @ResponseBody
    public String getSessionData(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("username");
    }

    @Transactional
    @GetMapping("/delete/{id}")
    public RedirectView deleteUserPost(@PathVariable String id, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        postRepository.deletePostByAppUser_UsernameAndId(username, Long.parseLong(id));

        return new RedirectView("/v2/posts");
    }
}
