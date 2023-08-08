package com.blogproject.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.blogproject.Security.CustomUserDetailService;
import com.blogproject.Security.JwtAuthenticationEntryPoint;
import com.blogproject.Security.JwtAuthenticationFilter;


@Configuration
@EnableWebSecurity
//@EnableWebMvc
//@EnableMethodSecurity(prePostEnabled = true)

public class SecurityConfig  {
	
	public static final String[] PUBLIC_URLS={
			"/api/v1/auth/**",
			"/v3/api-docs",
			"/v2/api-docs",
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/webjars/**",
            "/swagger-ui-custom.html"

	};

    @Autowired
    private JwtAuthenticationEntryPoint point;
    
    @Autowired
    private JwtAuthenticationFilter filter;
    
    @Autowired
    private CustomUserDetailService customUserDetailService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

 
    	

        http.csrf(csrf -> csrf.disable())
//        http.csrf(csrf -> csrf.ignoringRequestMatchers("/auth/login"))
//                .authorizeRequests().
                .cors(cors -> cors.disable())
        .authorizeHttpRequests (auth-> auth.requestMatchers(PUBLIC_URLS)
        		.permitAll()
//        		.requestMatchers().permitAll()
        		.requestMatchers(HttpMethod.GET).permitAll()
        	
//        		.authenticated()
//        		.requestMatchers("/auth/login")
        		
//        		.requestMatchers("auth/create-user")
//        		.permitAll()
                .anyRequest()
                .authenticated())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider doDaoAuthenticationProvider(){
    	
    	DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
    	daoAuthenticationProvider.setUserDetailsService(customUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    	
    }
//   protected void configure( AuthenticationManagerBuilder auth) throws Exception{
//	   auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
//   }
//@Bean
//private PasswordEncoder passwordEncoder() {
//	return new BCryptPasswordEncoder();

}


