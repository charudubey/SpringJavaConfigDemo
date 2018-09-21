package com.yash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yash.model.Login;
import com.yash.model.User;
import com.yash.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;

	@RequestMapping("/index")
	public ModelAndView hello() {
		
		String message="hello Spring MVC";
		ModelAndView mv=new ModelAndView("index");
		mv.addObject("message",message);
		return mv;
	}
	
	@RequestMapping("/helloWorld")
	public ModelAndView helloWorld() {
		
		String message="hello Spring MVC";
		ModelAndView mv=new ModelAndView("helloWorld");
		mv.addObject("message",message);
		return mv;
	}
	
	@RequestMapping(value = "/registration",method = RequestMethod.GET)
	public ModelAndView registration() {
		
		ModelAndView mv=new ModelAndView("registration");
		mv.addObject("user",new User());
		return mv;
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public ModelAndView login() {
		
		ModelAndView mv=new ModelAndView("login");
		 mv.addObject("login", new Login());
		return mv;
	}
	
	@RequestMapping(value = "/loginForm",method = RequestMethod.GET)
	public ModelAndView loginSubmit(@ModelAttribute("login") Login login) {
		
		ModelAndView mav = null;
	    User user = userService.validateUser(login.getUsername(), login.getPassword());

	    if (user != null) {
	    mav = new ModelAndView("helloWorld");
	    mav.addObject("firstname", user.getFirstname());
	    } else {
	    mav = new ModelAndView("login");
	    mav.addObject("message", "Username or Password is wrong!!");
	    }

		return mav;
	}
	
	@RequestMapping("/registerProcess")
	public ModelAndView registrationOfUser(@ModelAttribute("user") User user) {
		Integer id = userService.register(user);
		
		return new ModelAndView("helloWorld","user",id);
	}

	
}
