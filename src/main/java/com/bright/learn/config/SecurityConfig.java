package com.bright.learn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/*
 * @EnableWebSecurity 注解开启Spring Security的功能
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/security/helloSecurity",
                             "/my/hello", "/C08.jpg",
                             "/swagger-ui.html",
                             "/request-body",
                             "/model-attribute",
                             "/request-param",
                             "/local-date",
                             "/xml/**",
                             "/jdbc/**",
                             "/users").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin().loginPage("/security/login").permitAll().and()
            .logout().permitAll().and()
            .csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
        .withUser("user").password(new BCryptPasswordEncoder().encode("123456")).roles("USER");
    }
}
