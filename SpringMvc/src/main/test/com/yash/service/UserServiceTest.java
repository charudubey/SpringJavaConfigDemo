package com.yash.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.service.spi.InjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.dao.UserDao;
import com.yash.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	@Mock
	UserDao userDao;
	
	@InjectMocks
	UserService userService;

	@Test
	public void shouldAddUser(){
		User input = new User();
		input.setEmail("amit@gmail.com");
		input.setFirstname("Amit");
		input.setLastname("Sharma");
		input.setPassword("1234");
		input.setUsername("amits");
		input.setUserId(5);
		
		when(userDao.addUser(input)).thenReturn(input.getUserId());
		Integer actual = userService.register(input);
		assertEquals(input.getUserId(), actual);
		verify(userDao).addUser(input);
		
	}
	
	@Test
	public void shouldLoginAndReturnUser(){
		
		User input = new User();
		input.setEmail("amit@gmail.com");
		input.setFirstname("Amit");
		input.setLastname("Sharma");
		input.setPassword("1234");
		input.setUsername("amits");
		input.setUserId(5);
		
		when(userDao.validate(input.getUsername(), input.getPassword())).thenReturn(input);
		
		User actual = userService.validateUser(input.getUsername(), input.getPassword());
		assertEquals(input, actual);
		verify(userDao).validate(input.getUsername(), input.getPassword());
		
	}
	
}
