package com.example.springmvcboot;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String home() {
		return "index";
	}

	@GetMapping("/add")
	public String add(HttpServletRequest request) {
		int i= Integer.parseInt(request.getParameter("num1"));
		int j= Integer.parseInt(request.getParameter("num2"));

		int sum=i+j;

		HttpSession httpSession=request.getSession();

		httpSession.setAttribute("sum",sum);

		return "result";

	}


}
