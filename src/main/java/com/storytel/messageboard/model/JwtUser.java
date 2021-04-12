package com.storytel.messageboard.model;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;

@Document(collection="users")
@Data
@Builder
public class JwtUser {
    @Id
    @GeneratedValue(generator = "system-uuid")
    private  String id;
    private String userName;
    private String password;
    private String role;
    private  String email;
}
