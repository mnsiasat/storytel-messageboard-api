package com.storytel.messageboard.resolver;

import com.storytel.messageboard.model.JwtUser;
import com.storytel.messageboard.model.Message;
import com.storytel.messageboard.repository.JwtUserRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import com.storytel.messageboard.model.Author;
import com.storytel.messageboard.repository.AuthorRepository;
import com.storytel.messageboard.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Mutation implements GraphQLMutationResolver {

    private MessageRepository messageRepository;
    private AuthorRepository authorRepository;
    private JwtUserRepository userRepository;

    public Mutation(AuthorRepository authorRepository, MessageRepository messageRepository, JwtUserRepository userRepository) {
        this.authorRepository = authorRepository;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public Author newAuthor(String firstName, String lastName) {
        Author author = Author.builder().firstName(firstName).lastName(lastName).build();
        authorRepository.save(author);
        return author;
    }

    public Message newMessage(String title, String text, String category, String authorId) {
        Message message = Message.builder().title(title).category(category).build();
        message.setAuthor(Author.builder().id(authorId).build());

        messageRepository.save(message);
        return message;
    }

    public boolean deleteMessage(String id) {
        messageRepository.deleteById(id);
        return true;
    }

    public JwtUser createUser(String name, String password, String email) {
        final JwtUser newUser=JwtUser.builder().userName(name).password(password).email(email).build();
        newUser.setUserName(name);
        newUser.setPassword(password);
        newUser.setEmail(email);
        return userRepository.save(newUser);
    }
}
