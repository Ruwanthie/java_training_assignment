package com.dasuni.smsui.controller;

import com.dasuni.smsui.conf.AccessTokenConfigurer;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableOAuth2Sso

public class UIController extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest()
                .authenticated();

    }
    @RequestMapping(value = "/")
    public String loadHome(){
        return "index";
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @RequestMapping(value = "/add")
    public String addStudent(){
        return "home";
    }

    @PreAuthorize("hasRole('ROLE_admin') or hasRole('ROLE_operator')")
    @RequestMapping(value = "/find")
    public String findStudent(){
        return "searchstd";
    }

    @PreAuthorize("hasRole('ROLE_admin') or hasRole('ROLE_operator')")
    @RequestMapping(value = "/info")
    public String allStudents(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());

        return "info";
    }
}

