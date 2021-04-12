package com.storytel.messageboard.config;

import com.storytel.messageboard.security.JwtAuthenticationEntryPoint;
import com.storytel.messageboard.security.JwtAuthenticationProvider;
import com.storytel.messageboard.security.JwtAuthenticationTokenFilter;
import com.storytel.messageboard.security.JwtSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationManager authManager;
    private JwtAuthenticationEntryPoint entryPoint;

    public JwtSecurityConfig(AuthenticationManager authManager, JwtAuthenticationEntryPoint entryPoint){
        this.authManager = authManager;
        this.entryPoint = entryPoint;
    }

    public JwtAuthenticationTokenFilter getAuthTokenFilter() {
        JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
        filter.setAuthenticationManager(this.authManager);
        filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests().antMatchers("**/graphql/**").permitAll()
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(getAuthTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();
        System.out.println("/graphql patterns");

    }
}
