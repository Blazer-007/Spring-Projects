package com.blazer.joblisting.service;

import com.blazer.joblisting.model.Post;

import java.util.List;

public interface PostSearchService {

    List<Post> findByText(String text);
}
