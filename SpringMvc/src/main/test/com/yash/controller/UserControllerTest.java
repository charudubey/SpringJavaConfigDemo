package com.yash.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.yash.model.Login;
import com.yash.model.User;
import com.yash.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@Mock
	UserService userService;
	
	@InjectMocks
	UserController userController;
	
	@Test
	public void shouldCallHome() {
		
		ModelAndView actual = userController.hello();
		assertEquals("index", actual.getViewName());

	} 
	
	@Test
	public void shouldCallHelloWorld() {
		
		ModelAndView actual = userController.helloWorld();
		assertEquals("helloWorld", actual.getViewName());
	}
	
	@Test
	public void shouldCallRegistration() {
		
		ModelAndView actual = userController.registration(); 
		assertEquals("registration", actual.getViewName());
	}
	
	@Test
	public void shouldCallLoginPage() {
		
		ModelAndView actual = userController.login(); 
		assertEquals("login", actual.getViewName());
	}
	
	@Test
	public void shouldCallLoginForm() {
		Login input = new Login();
		input.setUsername("1234");
		input.setPassword("1234");
		
		User returnedUser = new User();
		returnedUser.setFirstname("Gary");
		returnedUser.setLastname("Mathews");
		returnedUser.setUserId(2);
		returnedUser.setUsername("1234");

		when(userService.validateUser(input.getUsername(), input.getPassword())).thenReturn(returnedUser);
		
		ModelAndView actual = userController.loginSubmit(input);
		assertEquals("helloWorld", actual.getViewName());
		verify(userService).validateUser(input.getUsername(), input.getPassword());
	}
	
	@Test
	public void shouldCallLoginFormAndReturnNull() {
		Login input = new Login();
		input.setUsername("1234");
		input.setPassword("1234");

		when(userService.validateUser(input.getUsername(), input.getPassword())).thenReturn(null);
		
		ModelAndView actual = userController.loginSubmit(input);
		assertEquals("login", actual.getViewName());
		verify(userService).validateUser(input.getUsername(), input.getPassword());
	}
	
	@Test
	public void shouldRegisterNewUser() {
		
		User user = new User();
		user.setFirstname("Gary");
		user.setLastname("Mathews");
		user.setUserId(2);
		user.setUsername("1234");

		when(userService.register(user)).thenReturn(null);
		
		ModelAndView actual = userController.registrationOfUser(user);
		assertEquals("helloWorld", actual.getViewName());
		verify(userService).register(user);
	}
	

}
