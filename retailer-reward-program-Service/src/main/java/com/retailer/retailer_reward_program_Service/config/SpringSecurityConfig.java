package com.retailer.retailer_reward_program_Service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
 
// Annotation
@EnableWebSecurity
// Class
public class SpringSecurityConfig extends WebSecurityConfiguration {
 
    // Annotation
    protected void configure(AuthenticationManagerBuilder auth)throws Exception
    {
        auth.inMemoryAuthentication()
            .passwordEncoder(passwordEncoder())
            .withUser("retailer")
            .password(passwordEncoder().encode("pass"))
            .roles("ADMIN")
            .and()
            .passwordEncoder(passwordEncoder())
            .withUser("customer")
            .password(passwordEncoder().encode("pass"))
            .roles("BASIC");
    }
 
    // Annotation
    @Bean
    // Method
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
 
   
    
    /* Method
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
 
        http.authorizeRequests()
            .antMatchers("/basic")
            .hasAnyRole("BASIC", "ADMIN")
            .antMatchers("/admin")
            .hasRole("ADMIN")
            .antMatchers("/")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .permitAll()
            .loginPage("/login")
            .usernameParameter("username")
            .and()
            .logout()
            .logoutRequestMatcher(
                new AntPathRequestMatcher("/logout"))
            .permitAll();
    }*/
}