package com.example.spectacle.security;

import com.example.spectacle.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

/*    @Autowired
    public void globalConfig(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("USER");
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder);
        //auth.inMemoryAuthentication().withUser("admin").password("{noop}123").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

       // http.authorizeRequests().antMatchers("/").permitAll().and().csrf().disable();

        http
                .authorizeRequests()
                    .anyRequest()
                        .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll();
    }
}