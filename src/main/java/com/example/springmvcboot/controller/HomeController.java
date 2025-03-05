package com.example.springmvcboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springmvcboot.model.Alien;
import com.example.springmvcboot.repository.AlienRepository;

@Controller
public class HomeController {

  @Autowired
  AlienRepository alienRepository;

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

  @GetMapping("/addAlien")
  public String addAlien(@ModelAttribute("alien") Alien a) {
    alienRepository.save(a);
    return "result";
  }

  @GetMapping("/getAliens")
  public String getAliens(Model m) {
    List<Alien> alienList = (List<Alien>) alienRepository.findAll();
    m.addAttribute("result", alienList);
    return "showAliens";
  }

  @GetMapping("/getAlienByID")
  public String getAlienByID(@RequestParam int aid, Model m) {
    m.addAttribute("alien", alienRepository.findById(aid));
    return "result";
  }

  @GetMapping("/getAlienByName")
  public String getAlienByName(@RequestParam String aname, Model m) {
    m.addAttribute("alien", alienRepository.find(aname));
    return "result";
  }

  @ModelAttribute
  public void modelData(Model m){
    m.addAttribute("name","Aliens");
  }
}
