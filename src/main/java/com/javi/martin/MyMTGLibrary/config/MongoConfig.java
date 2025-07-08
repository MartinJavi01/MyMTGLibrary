package com.javi.martin.MyMTGLibrary.config;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Value("${mongo.mongoUri}")
    private String mongoUri;
    @Value("${mongo.databaseName}")
    private String databaseName;
    @Value("${mongo.collectionName}")
    private String collectionName;

    @Bean
    public MongoCollection<Document> mongoCollection() {
        var mongoClient = MongoClients.create(mongoUri);
        var database = mongoClient.getDatabase(databaseName);
        return database.getCollection(collectionName);
    }
}
