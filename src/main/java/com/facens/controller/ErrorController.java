package com.facens.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {
	
	@GetMapping("/errorPage")
	public ModelAndView getError()
	{
		ModelAndView mv = new ModelAndView("ErrorTemplate");
		return mv;
	}
}
