package com.saitej.springsecurity_03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.saitej.springsecurity_03.config.ApplicationUserRole.*;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //Basic authentication
        http.authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*")
                .permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())//allows endpoint with only student role
               // .antMatchers("/api/**").hasRole(ADMIN.name())//allows endpoint with only admin role
                .anyRequest().authenticated().and()
                //.httpBasic();
             .formLogin();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails samUser = User.builder().username("sam").password(passwordEncoder().encode("sam")).roles(STUDENT.name()).build();
        UserDetails joeUser = User.builder().username("joe").password(passwordEncoder().encode("joe")).roles(ADMIN.name()).build();
        return new InMemoryUserDetailsManager(samUser,joeUser);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
