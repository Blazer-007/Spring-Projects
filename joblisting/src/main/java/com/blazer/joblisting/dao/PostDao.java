package com.blazer.joblisting.dao;

import com.blazer.joblisting.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/*
    @author Vivek Rai
    created on 15/10/23
*/
@Repository
public interface PostDao extends MongoRepository<Post, String> {
}
