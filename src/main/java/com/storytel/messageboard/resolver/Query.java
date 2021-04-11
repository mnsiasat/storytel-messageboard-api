package com.storytel.messageboard.resolver;

import com.storytel.messageboard.model.Message;
import graphql.kickstart.tools.GraphQLQueryResolver;
import com.storytel.messageboard.model.Author;
import com.storytel.messageboard.repository.AuthorRepository;
import com.storytel.messageboard.repository.MessageRepository;

import java.util.ArrayList;

public class Query implements GraphQLQueryResolver {

    private MessageRepository messageRepository;
    private AuthorRepository authorRepository;

    public Query(AuthorRepository authorRepository, MessageRepository messageRepository) {
        this.authorRepository = authorRepository;
        this.messageRepository = messageRepository;
    }

    public Iterable<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Iterable<Message> findAllMessages() {
        return new ArrayList<Message>();
    }

}
