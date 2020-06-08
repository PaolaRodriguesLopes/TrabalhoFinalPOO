package com.facens.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {
	
	@GetMapping("/errorPage")
	public ModelAndView getError(@RequestParam String message, String back)
	{
		ModelAndView mv = new ModelAndView("ErrorTemplate");
		mv.addObject("message", message);
		mv.addObject("back", back);
		return mv;
	}
}
