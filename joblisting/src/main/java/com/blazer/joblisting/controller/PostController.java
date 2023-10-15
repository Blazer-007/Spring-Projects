package com.blazer.joblisting.controller;

import com.blazer.joblisting.model.Post;
import com.blazer.joblisting.service.PostSearchService;
import com.blazer.joblisting.service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/*
    @author Vivek Rai
    created on 15/10/23
*/
@RestController
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    PostSearchService postSearchService;

    @RequestMapping(value="/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("posts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("posts/{text}")
    public List<Post> search(@PathVariable String text) {
        return postSearchService.findByText(text);
    }

    @PostMapping("post")
    public Post addPost(@RequestBody Post post) {
        return postService.addPost(post);
    }
}
