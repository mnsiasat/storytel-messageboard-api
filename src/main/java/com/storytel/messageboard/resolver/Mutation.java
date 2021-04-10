package com.storytel.messageboard.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.storytel.messageboard.model.Author;
import com.storytel.messageboard.repository.AuthorRepository;
import com.storytel.messageboard.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Mutation implements GraphQLMutationResolver {

    private MessageRepository messageRepository;
    private AuthorRepository authorRepository;

    public Mutation(AuthorRepository authorRepository, MessageRepository messageRepository) {
        this.authorRepository = authorRepository;
        this.messageRepository = messageRepository;
    }

    public Author newAuthor(String firstName, String lastName) {
        Author author = Author.builder().firstName(firstName).lastName(lastName).build();
        authorRepository.save(author);
        return author;
    }

}
