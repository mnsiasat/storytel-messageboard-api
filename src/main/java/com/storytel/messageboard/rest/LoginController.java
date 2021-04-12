package com.storytel.messageboard.rest;

import com.storytel.messageboard.exception.CreateUserException;
import com.storytel.messageboard.exception.InvalidLoginException;
import com.storytel.messageboard.model.JwtUser;
import com.storytel.messageboard.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);


    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(path = "/create-user",produces = "application/json")
    public ResponseEntity createUser(@RequestBody final JwtUser jwtUser){
        ResponseEntity responseEntity = null;
        JwtUser fetchedUser = null;

        try {
            loginService.createUser(jwtUser);
            responseEntity = new ResponseEntity<String>("User successfully created!", HttpStatus.CREATED);

        } catch (CreateUserException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Failed to create user: ", e);
            responseEntity = new ResponseEntity<String>("Failed to create user check your parameters!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping(path = "/login",produces = "application/json")
    public ResponseEntity login(@RequestBody final JwtUser jwtUser) {
        ResponseEntity responseEntity = null;
        JwtUser fetchedUser = null;

        try {
            final String token = loginService.login(jwtUser);
            responseEntity = new ResponseEntity<String>(token, HttpStatus.OK);

        } catch (InvalidLoginException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Failed to login please check your credentials! ", e);
            responseEntity = new ResponseEntity<String>("Failed to login please check your credentials!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
