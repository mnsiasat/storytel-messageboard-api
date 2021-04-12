package com.storytel.messageboard.repository;

import com.storytel.messageboard.model.JwtUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtUserRepository extends MongoRepository<JwtUser, String> {

    @Query("{ 'userName' : ?0 }")
    JwtUser findByUserName(String userName);

}