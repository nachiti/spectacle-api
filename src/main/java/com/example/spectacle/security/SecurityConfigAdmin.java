package com.example.spectacle.security;

import com.example.spectacle.service.AdminDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Order(2)
@EnableWebSecurity
//public class SecurityConfigAdmin{}

public class SecurityConfigAdmin extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdminDetailServiceImpl adminDetailService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminDetailService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

      //  http.authorizeRequests().antMatchers("/").permitAll().and().csrf().disable();
       // http.antMatcher("/admin/**").authorizeRequests().anyRequest().authenticated();

        http
                .antMatcher("/admin/**")
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/admin/login").permitAll();
    }
}
