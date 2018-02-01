package com.okta.prasad.oktaweb2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	// Inject via application.properties
	@Value("${welcome.message}")
	private String message;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {

		model.addAttribute("message", message);

		return "index";
	}

}
