package com.storytel.messageboard.security;

import com.storytel.messageboard.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

public class JwtValidator {


    private String secret = "Graphql";

    public JwtUser validate(String token) {

        JwtUser jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = JwtUser.builder().userName(body.getSubject())
                    .password((String) body.get("userId"))
                    .role((String) body.get("role"))
                    .build();
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return jwtUser;
    }
}
