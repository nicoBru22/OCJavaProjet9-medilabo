package com.medilabo.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class NavigationController {
	
	private Logger logger = LogManager.getLogger();
	
	@GetMapping("/")
	public String getHomePage(Model model) {
		logger.info("Entrée dans controller / pour afficher page d'accueil (home.html).");
		model.addAttribute("test", "bienvenue sur la page d'accueil de MediLabo");
		return "homePage";
	}

}
