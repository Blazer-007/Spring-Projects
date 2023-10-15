package com.blazer.joblisting.service;

import com.blazer.joblisting.model.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    @author Vivek Rai
    created on 15/10/23
*/
@Component
public class PostSearchServiceImpl implements PostSearchService {

    @Autowired
    MongoClient mongoClient;

    @Autowired
    MongoConverter mongoConverter;

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Value("${spring.data.mongodb.database.collection}")
    private String collectionName;

    @Override
    public List<Post> findByText(String text) {
        List<Post> posts = new ArrayList<>();

        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<org.bson.Document> collection = database.getCollection(collectionName);

        AggregateIterable<Document> result = collection.aggregate(
                Arrays.asList(
                        new Document("$search",
                        new Document("index", "PostSearchIndex")
                                .append("text",new Document("query", text)
                                        .append("path", Arrays.asList("desc", "techs", "profile")))),
                        new Document("$sort",new Document("exp", -1L)),
                        new Document("$limit", 10L))
        );

        result.forEach(doc -> posts.add(mongoConverter.read(Post.class, doc)));

        return posts;
    }
}
