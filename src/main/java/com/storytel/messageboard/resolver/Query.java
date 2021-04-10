package com.storytel.messageboard.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.storytel.messageboard.model.Author;
import com.storytel.messageboard.repository.AuthorRepository;
import com.storytel.messageboard.repository.MessageRepository;

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

}
