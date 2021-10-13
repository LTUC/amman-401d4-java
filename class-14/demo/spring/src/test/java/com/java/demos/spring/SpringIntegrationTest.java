package com.java.demos.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.demos.spring.models.AppUser;
import com.java.demos.spring.models.Post;
import com.java.demos.spring.repositories.UserRepository;
import com.java.demos.spring.repositories.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class SpringIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createNewPostWorksThroughAllLayers() throws Exception {
        String username = "Khair";
        String email = "khair@gmail.com";
        AppUser appUser = new AppUser(username, email);
        userRepository.save(appUser);
        Post post = new Post(appUser, "Bezos");

        mockMvc.perform(post("/v1/posts")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isOk());

        Post foundPost = postRepository.findById(1L).orElseThrow();
        Assertions.assertEquals(post.getUser().getUsername(), foundPost.getUser().getUsername());
    }

    @Test
    void signUpAuthorWorksThroughAllLayersTest() {
        String username = "Khair";
        String email = "khair@gmail.com";
        AppUser appUser = new AppUser(username, email);

        try {
            mockMvc.perform(post("/signup/users")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .content(buildUrlEncodedFormEntity(
                            "username", appUser.getUsername(),
                            "email", appUser.getEmail())));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        AppUser savedAppUser = userRepository.findAppUsersByUsername(username).orElseThrow();
        Assertions.assertEquals(appUser.getUsername(), savedAppUser.getUsername());
    }

    /**
     * Taken from
     *
     * https://stackoverflow.com/questions/36568518/testing-form-posts-through-mockmvc
     *
     * @param params
     * @return
     */
    private String buildUrlEncodedFormEntity(String... params) {
        if( (params.length % 2) > 0 ) {
            throw new IllegalArgumentException("Need to give an even number of parameters");
        }
        StringBuilder result = new StringBuilder();
        for (int i=0; i<params.length; i+=2) {
            if( i > 0 ) {
                result.append('&');
            }
            try {
                result.
                        append(URLEncoder.encode(params[i], StandardCharsets.UTF_8.name())).
                        append('=').
                        append(URLEncoder.encode(params[i+1], StandardCharsets.UTF_8.name()));
            }
            catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return result.toString();
    }
}
