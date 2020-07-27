package com.example.spectacle.security;

import com.example.spectacle.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void globalConfig(AuthenticationManagerBuilder auth) throws Exception {
       // auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("USER");
        auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder);
    }

 /*   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder);
    }
*/

    @Configuration
    @Order(1)
    public static class SecurityConfigureApi extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/api/**")
                    .authorizeRequests()
                    .antMatchers("/api/user/**").hasAuthority("USER")
                    .antMatchers("/api/public/**").permitAll()
                    .and()
                    .httpBasic()
            .and()
            .csrf().disable();
        }

    }

    @Configuration
    @Order(2)
    public static class SecurityConfigureAdmin extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/admin/**")
                    .authorizeRequests()
                    .antMatchers("/admin/**").hasAuthority("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/admin/login").permitAll()
                    .defaultSuccessUrl("/admin/index")
                    .and()
                    .logout().permitAll()
                    .logoutSuccessUrl("/admin/login")
                    .and()
                    .exceptionHandling().accessDeniedPage("/admin/login");
        }

    }

    @Configuration
    @Order(3)
    public static class SecurityConfigure extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/*")
                    .authorizeRequests()
                    .antMatchers("/admin/**").hasAuthority("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/admin/login").permitAll()
                    .defaultSuccessUrl("/admin/index")
                    .and()
                    .logout().permitAll()
                    .logoutSuccessUrl("/admin/login")
                    .and()
                    .exceptionHandling().accessDeniedPage("/admin/login");
        }

    }
}