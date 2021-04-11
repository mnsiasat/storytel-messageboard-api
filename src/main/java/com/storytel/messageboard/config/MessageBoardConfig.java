package com.storytel.messageboard.config;

import com.storytel.messageboard.repository.AuthorRepository;
import com.storytel.messageboard.repository.MessageRepository;
import com.storytel.messageboard.resolver.MessageResolver;
import com.storytel.messageboard.resolver.Mutation;
import com.storytel.messageboard.resolver.Query;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories({ "com.storytel.messageboard.repository" })
public class MessageBoardConfig {

    @Bean
    public Query query(AuthorRepository authorRepository, MessageRepository messageRepository) {
        return new Query(authorRepository, messageRepository);
    }

    @Bean
    public Mutation mutation(AuthorRepository authorRepository, MessageRepository messageRepository) {
        return new Mutation(authorRepository, messageRepository);
    }

    @Bean
    public MessageResolver messageResolver(AuthorRepository authorRepository) {
        return new MessageResolver(authorRepository);
    }

}
