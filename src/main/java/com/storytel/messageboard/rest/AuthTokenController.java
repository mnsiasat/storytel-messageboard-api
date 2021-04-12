package com.storytel.messageboard.rest;

import com.storytel.messageboard.model.JwtUser;
import com.storytel.messageboard.security.JwtGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login")
public class AuthTokenController {

    private Logger logger = LoggerFactory.getLogger(AuthTokenController.class);

    private JwtGenerator jwtGenerator;

    public AuthTokenController() {
        this.jwtGenerator = new JwtGenerator();
    }

    @PostMapping
    public ResponseEntity generate(@RequestBody final JwtUser jwtUser) {
        ResponseEntity responseEntity = null;

        try {
            logger.info("INSIDE generate token: "+ jwtUser);
            final String token = jwtGenerator.generate(jwtUser);

            responseEntity = new ResponseEntity<String>(token,HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Failed to login please check your credentials! ",e);
            responseEntity = new ResponseEntity("Failed to add movie", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
