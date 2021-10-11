package com.d4coders.restful.repository;

import com.d4coders.restful.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Post findPostByAuthor(String name);
}