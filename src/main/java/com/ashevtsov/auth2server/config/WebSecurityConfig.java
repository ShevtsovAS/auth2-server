package com.ashevtsov.auth2server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("alex")
                .password("{noop}alexpass")
                .roles("ADMIN", "USER", "MANAGER")
                .authorities("CAN_READ", "CAN_WRITE", "CAN_DELETE")
                .and()
                .withUser("oleg")
                .password("{noop}olegpass")
                .roles("USER")
                .authorities("CAN_READ");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
