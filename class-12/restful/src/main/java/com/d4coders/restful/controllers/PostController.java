package com.d4coders.restful.controllers;

import com.d4coders.restful.models.Post;
import com.d4coders.restful.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository;

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
    public RedirectView createNewBlogPost(@ModelAttribute Post post) { // modelattribute when working with fomr data
        postRepository.save(post);
        return new RedirectView("posts");
    }

    @GetMapping("/v2/posts/{author}")
    public String findPostByAuthor(@PathVariable String author, Model model) {
        Post post = postRepository.findPostByAuthor(author);
        model.addAttribute("post", post);

        return "post";
    }
}
