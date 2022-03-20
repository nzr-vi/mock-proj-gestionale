package it.es.gestionale.utility;

import org.springframework.ui.Model;

public class Errors {

	public static String accessDeniedMVC(Model model, String message) {
		model.addAttribute("message", message);
		return "error";
	}
	
	public static String accessDeniedMVC(Model model) {
		return accessDeniedMVC(model, "access denied");
	}
}

