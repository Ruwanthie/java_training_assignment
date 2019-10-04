package com.dasuni.smsui.controller;

import com.dasuni.smsui.conf.AccessTokenConfigurer;
import mainModule.modal.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@EnableOAuth2Sso

public class UIController extends WebSecurityConfigurerAdapter {

    @Autowired
    RestTemplate restTemplate;

    @Bean
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

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

    @RequestMapping(value = "/add")
    public String addStudent(){
        return "home";
    }


    @RequestMapping(value = "/find")
    public String findStudent(){
        return "searchstd";
    }


    @RequestMapping(value = "/info")
    public String allStudents(Model model){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Student> studentHttpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Student[]> responseEntity = restTemplate.exchange("http://localhost:8081/sms/student", HttpMethod.GET,studentHttpEntity,Student[].class);
        model.addAttribute("students", responseEntity.getBody());
        return "info";
    }
}

