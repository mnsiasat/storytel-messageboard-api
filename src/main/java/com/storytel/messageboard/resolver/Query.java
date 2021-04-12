package com.storytel.messageboard.resolver;

import com.storytel.messageboard.model.JwtUser;
import com.storytel.messageboard.model.Message;
import com.storytel.messageboard.repository.JwtUserRepository;
import com.storytel.messageboard.security.JwtValidator;
import graphql.kickstart.tools.GraphQLQueryResolver;
import com.storytel.messageboard.model.Author;
import com.storytel.messageboard.repository.AuthorRepository;
import com.storytel.messageboard.repository.MessageRepository;

import java.util.ArrayList;

public class Query implements GraphQLQueryResolver {

    private MessageRepository messageRepository;
    private AuthorRepository authorRepository;
    private JwtUserRepository userRepository;
    private JwtValidator jwtValidator;

    public Query(AuthorRepository authorRepository, MessageRepository messageRepository, JwtUserRepository userRepository) {
        this.authorRepository = authorRepository;
        this.messageRepository = messageRepository;
        this.userRepository =  userRepository;
        jwtValidator = new JwtValidator();
    }

    public Iterable<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Iterable<Message> findAllMessages() {
        return new ArrayList<Message>();
    }

}
