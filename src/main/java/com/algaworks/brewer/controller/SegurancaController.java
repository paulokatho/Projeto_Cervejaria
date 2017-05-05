package com.algaworks.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SegurancaController {
	
	@GetMapping("/login")
	public String login() {
		
		return "Login"; 
	}
}
