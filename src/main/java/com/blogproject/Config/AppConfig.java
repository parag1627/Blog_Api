package com.blogproject.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class AppConfig {
	
	
//	    @Bean
//	    public UserDetailsService detailsService(){
//	        UserDetails user = User.builder().username("parag").password(passwordEncoder().encode("abc")).roles("ADMIN").build();
//	        UserDetails user1 = User.builder().username("sunny").password(passwordEncoder().encode("abc")).roles("ADMIN").build();
//	        UserDetails user3 = User.builder().username("lalit").password(passwordEncoder().encode("abc")).roles("ADMIN").build();
	//
	//
//	        return new InMemoryUserDetailsManager(user,user1,user3);
	//
//	    }

	    @Bean
	    public PasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();

	    }
	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
	        return builder.getAuthenticationManager();
	    }
	}


