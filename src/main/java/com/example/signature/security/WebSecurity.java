package com.example.signature.security;

import com.example.signature.filter.FilterSignature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Bean
    public SecurityFilterChain configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()
                .addFilterAfter(filterSignature(), BasicAuthenticationFilter.class)
                .antMatcher("/product");//custom filter, will filter request from client to make sure the signature is valid
        return httpSecurity.build();
    }

    @Bean
    public FilterSignature filterSignature(){
        return new FilterSignature();
    }//add custom filter into spring security filter chain
}
