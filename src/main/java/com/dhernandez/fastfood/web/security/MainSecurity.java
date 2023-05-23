package com.dhernandez.fastfood.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dhernandez.fastfood.domain.services.UserDetailServiceImpl;

 /* 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 17/05/2023
 */
@Configuration
public class MainSecurity {
	
	    @Autowired
	    JwtEntryPoint jwtEntryPoint;
	  
	    @Autowired
	    UserDetailServiceImpl userDetailsService;

	    @Autowired
	    private JwtTokenFilter jwtFilter;
	    @Bean
	    AuthenticationManager  authenticationManager(HttpSecurity http) throws Exception {
	        return http.getSharedObject(AuthenticationManagerBuilder.class)
	                .userDetailsService(userDetailsService)
	                .passwordEncoder(passwordEncoder()).and().build();
	    }

	  

	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager manager) throws Exception {

	        return http
	                .csrf()
	                .disable()
	                .cors()
	                .and()
	                .authorizeHttpRequests()
	                .requestMatchers("/auth/**","/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html")
	                .permitAll()
	                .anyRequest()
	                .authenticated()
	                .and()
	                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
	                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	                .and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
	                .build();
	    }
	    @Bean
	    PasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();
	    }
	    
	   
}
