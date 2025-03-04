package com.example.springmvcboot;

import com.example.springmvcboot.model.Alien;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

  @GetMapping("/home")
  public String home() {
    return "index";
  }

  @GetMapping("/add")
  public String add(@RequestParam("num1") int i, @RequestParam("num2") int j, ModelMap modelMap) {
    int sum = i + j;

    modelMap.addAttribute("sum", sum);

    return "result";
  }

  @GetMapping("addAlien")
  public String addAlien(@ModelAttribute("alien") Alien a) {
    return "result";
  }

  @ModelAttribute
  public void modelData(Model m){
    m.addAttribute("name","Aliens");
  }
}
