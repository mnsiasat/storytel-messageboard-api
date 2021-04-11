package com.storytel.messageboard.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import java.util.UUID;

@Document(collection = "authors")
@Data
@Builder
public class Author{

    @Id
    @GeneratedValue(generator = "system-uuid")
    private String id;
    private String firstName;
    private String lastName;
}