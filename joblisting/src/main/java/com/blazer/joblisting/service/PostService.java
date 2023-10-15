package com.blazer.joblisting.service;

import com.blazer.joblisting.dao.PostDao;
import com.blazer.joblisting.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    @author Vivek Rai
    created on 15/10/23
*/
@Service
public class PostService {

    @Autowired
    PostDao postDao;

    public List<Post> getAllPosts() {
        return postDao.findAll();
    }

    public Post addPost(Post post) {
        return postDao.save(post);
    }
}
