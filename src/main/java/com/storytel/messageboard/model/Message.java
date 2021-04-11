package com.storytel.messageboard.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import java.util.UUID;

@Document(collection = "messages")
@Data
@Builder
public class Message {
    @Id
    @GeneratedValue(generator = "system-uuid")
    private String id;
    private String title;
    private String text;
    private String category;
    private Author author;
}
