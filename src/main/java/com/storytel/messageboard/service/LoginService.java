package com.storytel.messageboard.service;

import com.storytel.messageboard.exception.CreateUserException;
import com.storytel.messageboard.exception.InvalidLoginException;
import com.storytel.messageboard.model.JwtUser;
import com.storytel.messageboard.repository.JwtUserRepository;
import com.storytel.messageboard.security.JwtGenerator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginService {
    private JwtUserRepository userRepository;
    private JwtGenerator jwtGenerator;

    private final String EMAIL_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public LoginService(JwtUserRepository userRepository) {
        this.userRepository = userRepository;
        this.jwtGenerator = new JwtGenerator();
    }

    public void createUser(JwtUser user) throws CreateUserException {
        if (user.getPassword() == null || user.getEmail() == null ) {
            throw new CreateUserException("Missing required parameters: email and password!");
        }

        if(!isValidEmail(user.getEmail())){
            throw new CreateUserException("Invalid Email!");
        }
        userRepository.save(user);
    }

    public String login(JwtUser user) throws InvalidLoginException {
        JwtUser fetchedUser = null;

        if (user.getPassword() == null || (user.getUserName() == null && user.getEmail() == null)) {
            throw new InvalidLoginException("Missing required parameter: userName/email, password!");
        }

        if (user.getUserName() != null) {
            fetchedUser = userRepository.findByUserName(user.getUserName());
        } else {
            fetchedUser = userRepository.findByEmail(user.getEmail());
        }

        if (fetchedUser == null) {
            throw new InvalidLoginException("User does not exist!");
        }

        if (!user.getPassword().equals(fetchedUser.getPassword())) {
            throw new InvalidLoginException(String.format("Password does not match! %s : %s", user.getPassword(),fetchedUser.getPassword()));
        }
        return jwtGenerator.generate(user);
    }

    public boolean isValidEmail(final String email) {
        final Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
