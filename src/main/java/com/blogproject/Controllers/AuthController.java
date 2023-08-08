package com.blogproject.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.blogproject.Payload.JwtAuthRequest;
import com.blogproject.Payload.JwtAuthResponse;
import com.blogproject.Payload.UserDto;
import com.blogproject.Security.JwtHelper;
import com.blogproject.Services.UserService;



@RestController

@RequestMapping("/api/v1/auth/")

//@CrossOrigin

//@CrossOrigin (origins = "http://localhost:58296" , exposedHeaders = "token")

public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;
    
    @Autowired
    private UserService userService;
   

    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) {

        this.doAuthenticate(request.getUsername(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);

        JwtAuthResponse  response = new JwtAuthResponse();
        response.setToken(token);
        
        
                
        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }
    
    
    
    

    private void doAuthenticate(String username, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            this.manager.authenticate(authentication);

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler () {
        return "Credentials Invalid !!";
    }
    
    //register new API
    
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userdto) {
    		UserDto registerNewUser = this.userService.registerNewUser(userdto);
    		
    		return new ResponseEntity<UserDto>(registerNewUser,HttpStatus.CREATED);
    }
}