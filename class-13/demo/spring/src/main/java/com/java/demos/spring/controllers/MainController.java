package com.java.demos.spring.controllers;

import com.java.demos.spring.models.Author;
import com.java.demos.spring.models.Post;
import com.java.demos.spring.models.dto.PostDTO;
import com.java.demos.spring.repositories.AuthorRepository;
import com.java.demos.spring.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class MainController {

    // field injection
    @Autowired
    private PostRepository postRepository;

    // this way is better
    // constructor injection
    private final AuthorRepository authorRepository;
    public MainController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @ResponseBody
    @PostMapping("/v1/posts")
    public Post createNewPost(@RequestBody Post post) { // when working with json data
        Post newPost = postRepository.save(post);
        return newPost;
    }

    @GetMapping("/v2/posts")
    public String getBlogPosts(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "posts";
    }

    @PostMapping("/v2/posts")
    public RedirectView createNewBlogPost(@ModelAttribute PostDTO postDTO) { // modelattribute when working with fomr data
        Author author = authorRepository.findAuthorByUsername(postDTO.getAuthor()).orElseThrow();
        Post newPost = new Post(author, postDTO.getContent());
        postRepository.save(newPost);

        return new RedirectView("posts");
    }

    @GetMapping("/v2/posts/authors/{author}")
    public String findPostByAuthor(@PathVariable String author, Model model) {
        List<Post> posts = postRepository.findAllByAuthor_Username(author);
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

    @PostMapping("/signup/authors")
    public RedirectView signUpAuthor(@ModelAttribute Author author) {
        authorRepository.save(author);

        // we should then show the post creation page
        return new RedirectView("/v2/posts");
    }
}
