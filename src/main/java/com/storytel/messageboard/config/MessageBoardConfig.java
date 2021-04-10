package com.storytel.messageboard.config;


import com.storytel.messageboard.exception.GraphQLErrorAdapter;
import com.storytel.messageboard.repository.AuthorRepository;
import com.storytel.messageboard.repository.MessageRepository;
import com.storytel.messageboard.resolver.Mutation;
import com.storytel.messageboard.resolver.Query;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public GraphQLErrorHandler errorHandler() {
        return new GraphQLErrorHandler() {
            @Override
            public List<GraphQLError> processErrors(List<GraphQLError> errors) {
                List<GraphQLError> clientErrors = errors.stream()
                        .filter(this::isClientError)
                        .collect(Collectors.toList());

                List<GraphQLError> serverErrors = errors.stream()
                        .filter(e -> !isClientError(e))
                        .map(GraphQLErrorAdapter::new)
                        .collect(Collectors.toList());

                List<GraphQLError> e = new ArrayList<>();
                e.addAll(clientErrors);
                e.addAll(serverErrors);
                return e;
            }

            protected boolean isClientError(GraphQLError error) {
                return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
            }
        };
    }

}
