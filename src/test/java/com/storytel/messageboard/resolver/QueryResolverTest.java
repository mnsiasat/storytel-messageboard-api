package com.storytel.messageboard.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.storytel.messageboard.model.Author;
import com.storytel.messageboard.repository.AuthorRepository;
import com.storytel.messageboard.repository.JwtUserRepository;
import com.storytel.messageboard.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@GraphQLTest
public class QueryResolverTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @MockBean
    private MessageRepository messageRepository;
    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private JwtUserRepository userRepository;

    @Test
    public void findAllAuthors() throws IOException {
        final Author author = Author.builder().firstName("jane").lastName("doe").build();
        when(authorRepository.findAll())
                .thenReturn(Arrays.asList(author));

        final GraphQLResponse response =
                graphQLTestTemplate.postForResource("findAllAuthors.graphql");

        assertTrue(response.isOk());
        ObjectMapper mapper = new ObjectMapper();

        System.out.println(mapper.writeValueAsString(response));
        //assertEquals("1", response.get("$.data"));
        //assertEquals("Pikachu", response.get("$.data.pokemon.name"));
    }

}
