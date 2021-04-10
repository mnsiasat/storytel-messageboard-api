package com.storytel.messageboard.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.storytel.messageboard.model.Author;
import com.storytel.messageboard.model.Message;
import com.storytel.messageboard.repository.AuthorRepository;

public class MessageResolver implements GraphQLResolver<Message> {
    private AuthorRepository authorRepository;

    public MessageResolver(AuthorRepository repository) {
        this.authorRepository = repository;
    }

    public Author getAuthor(Message message) {
        //return authorRepository.findOne(book.getAuthor().getId());
        return Author.builder().firstName("myra").lastName("siasat").build();
    }
}
