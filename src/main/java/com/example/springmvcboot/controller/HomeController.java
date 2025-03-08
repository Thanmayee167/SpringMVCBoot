package com.example.springmvcboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springmvcboot.model.Alien;
import com.example.springmvcboot.repository.AlienRepository;

/**
 * Controller class handling web requests for the alien management system.
 * This controller provides endpoints for various operations including:
 * - Home page navigation
 * - Login/logout functionality
 * - Alien CRUD operations
 * - Basic arithmetic operations
 */
@Controller
public class HomeController {

  @Autowired
  AlienRepository alienRepository;

  /**
   * Handles GET requests to the home page.
   * This endpoint serves as the main entry point for the application's web interface.
   *
   * @return the name of the index view template
   */
  @GetMapping("/home")
  public String home() {
    return "index";
  }

  /**
   * Handles POST requests to the home endpoint.
   * This endpoint is used for form submissions from result.jsp and redirects back to the home page.
   * The redirect prevents form resubmission issues and maintains proper RESTful behavior.
   *
   * @return a redirect instruction to the home page
   */
  @PostMapping("/home")
  public String homePost() {
    return "redirect:/home";
  }

  /**
   * Handles GET requests to the login page.
   * Displays the login form for user authentication.
   *
   * @return the name of the login view template
   */
  @GetMapping("/login")
  public String loginPage()
  {
    return "login";
  }

  /**
   * Handles GET requests to the logout success page.
   * Displayed after a successful logout operation.
   *
   * @return the name of the logout view template
   */
  @GetMapping("/logout-success")
  public String logoutPage()
  {
    return "logout";
  }

  /**
   * Handles addition operation between two numbers.
   * This endpoint demonstrates basic arithmetic and model attribute usage.
   *
   * @param i first number to add
   * @param j second number to add
   * @param modelMap the model map to store the result
   * @return the name of the result view template
   */
  @GetMapping("/add")
  public String add(@RequestParam("num1") int i, @RequestParam("num2") int j, ModelMap modelMap) {
    int sum = i + j;

    modelMap.addAttribute("sum", sum);

    return "result";
  }

  /**
   * Handles the creation of a new alien entity.
   * Saves the provided alien object to the database.
   *
   * @param a the alien object to be saved, populated from form data
   * @return the name of the result view template
   */
  @GetMapping("/addAlien")
  public String addAlien(@ModelAttribute("alien") Alien a) {
    alienRepository.save(a);
    return "result";
  }

  /**
   * Retrieves and displays all aliens in the database.
   * 
   * @param m the model to add the list of aliens to
   * @return the name of the view template for displaying aliens
   */
  @GetMapping("/getAliens")
  public String getAliens(Model m) {
    List<Alien> alienList = (List<Alien>) alienRepository.findAll();
    m.addAttribute("result", alienList);
    return "showAliens";
  }

  /**
   * Retrieves and displays an alien by its ID.
   *
   * @param aid the ID of the alien to retrieve
   * @param m the model to add the alien object to
   * @return the name of the result view template
   */
  @GetMapping("/getAlienByID")
  public String getAlienByID(@RequestParam int aid, Model m) {
    m.addAttribute("alien", alienRepository.findById(aid));
    return "result";
  }

  /**
   * Retrieves and displays aliens by name.
   *
   * @param aname the name of the alien(s) to search for
   * @param m the model to add the search results to
   * @return the name of the result view template
   */
  @GetMapping("/getAlienByName")
  public String getAlienByName(@RequestParam String aname, Model m) {
    m.addAttribute("alien", alienRepository.find(aname));
    return "result";
  }

  /**
   * Adds common model attributes that should be available to all views.
   * This method is called automatically for all request handlers in this controller.
   *
   * @param m the model to add the common attributes to
   */
  @ModelAttribute
  public void modelData(Model m){
    m.addAttribute("name","Aliens");
  }
}
