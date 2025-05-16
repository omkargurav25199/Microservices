package com.example.restfulservices.demo_restapi_services.security;


import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		//All request should be authenticated
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		
		//Using Basis Authentiaction by using popup
		http.httpBasic(withDefaults());
		
		//Disabeling CSRF so we can send a POST Request
		http.csrf().disable();
		
		
		return http.build();
	}

}
