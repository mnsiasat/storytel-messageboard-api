package com.storytel.messageboard.repository;

import com.storytel.messageboard.model.Author;
import com.storytel.messageboard.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
}