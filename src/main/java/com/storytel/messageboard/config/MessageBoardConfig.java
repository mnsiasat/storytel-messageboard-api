package com.storytel.messageboard.config;

import com.storytel.messageboard.repository.AuthorRepository;
import com.storytel.messageboard.repository.JwtUserRepository;
import com.storytel.messageboard.repository.MessageRepository;
import com.storytel.messageboard.resolver.MessageResolver;
import com.storytel.messageboard.resolver.Mutation;
import com.storytel.messageboard.resolver.Query;
import com.storytel.messageboard.rest.LoginController;
import com.storytel.messageboard.security.JwtAuthenticationEntryPoint;
import com.storytel.messageboard.security.JwtAuthenticationProvider;
import com.storytel.messageboard.security.JwtValidator;
import com.storytel.messageboard.service.LoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;

import java.util.Collections;

@Configuration
@EnableMongoRepositories({ "com.storytel.messageboard.repository" })
public class MessageBoardConfig {

    @Bean
    public Query query(AuthorRepository authorRepository, MessageRepository messageRepository,JwtUserRepository userRepository) {
        return new Query(authorRepository, messageRepository, userRepository);
    }

    @Bean
    public Mutation mutation(AuthorRepository authorRepository, MessageRepository messageRepository, JwtUserRepository userRepository) {
        return new Mutation(authorRepository, messageRepository,userRepository);
    }

    @Bean
    public MessageResolver messageResolver(AuthorRepository authorRepository) {
        return new MessageResolver(authorRepository);
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        final JwtValidator validator = new JwtValidator();
        final JwtAuthenticationProvider authProvider = new JwtAuthenticationProvider(validator);
        return new ProviderManager(Collections.singletonList(authProvider));
    }

    @Bean
    public JwtSecurityConfig jwtSecurityConfig(){
        final JwtAuthenticationEntryPoint authEntryPoint = new JwtAuthenticationEntryPoint();
        final JwtSecurityConfig securityConfig = new JwtSecurityConfig(authenticationManager(), authEntryPoint);
        return securityConfig;
    }

    @Bean
    public LoginService loginService(JwtUserRepository userRepository){
        return new LoginService(userRepository);
    }

    @Bean
    public LoginController authTokenController(LoginService loginService){
        return new LoginController(loginService);
    }
}
