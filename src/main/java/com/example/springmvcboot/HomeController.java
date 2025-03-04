package com.example.springmvcboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String home() {
		return "index";
	}

	@GetMapping("/add")
	public ModelAndView add(@RequestParam("num1") int i, @RequestParam("num2") int j) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result");
		int sum = i + j;

		mv.addObject("sum", sum);

		return mv;
	}
}
