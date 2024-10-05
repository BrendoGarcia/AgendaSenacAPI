package com.agendasenac.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agendasenac.modells.JwtResponse;
import com.agendasenac.modells.LoginRequest;
import com.agendasenac.services.AuthenticationService;
import com.agendasenac.util.CodigoUtilRapido;

@RestController
public class LoginController {
	
	@Autowired
    private AuthenticationService authenticationService;
	
	
	@PostMapping("/login")
	@CrossOrigin
	public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest authRequest) {
	        try {
	            // Autentica o usuário e gera o token
	            String token = authenticationService.authenticate(authRequest.getUserEmail(), authRequest.getUserSenha());
	            
	            Map<String, String> response = new HashMap<>();
		        response.put("Token ",  token);
	            
	            return ResponseEntity.ok(response);
	        } catch (AuthenticationException e) {
	            // Se houver erro de autenticação, retorne 401 Unauthorized
	        	Map<String, String> response = new HashMap<>();
		        response.put("Aceso ",  "Negado");
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	        }
	}

		
		
		

}