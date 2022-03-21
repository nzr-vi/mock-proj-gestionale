package it.es.gestionale.utility;

import org.springframework.ui.Model;

public class Errors {

	public static String genericErrorMVC(Model model, String message) {
		model.addAttribute("message", message);
		return "error";
	}
	
	public static String accessDeniedMVC(Model model) {
		return genericErrorMVC(model, "access denied");
	}
	
	public static String paramErrorMVC(Model model) {
		return genericErrorMVC(model, "param error");
	}
}

