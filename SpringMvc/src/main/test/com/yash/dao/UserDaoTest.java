package com.yash.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {

	@Mock
	EntityManager entityManager;
	
	@Mock
	Query query;
	
	@InjectMocks
	UserDao userDao;
	
	@Test
	public void shouldAddUser(){
		User input = new User();
		input.setEmail("amit@gmail.com");
		input.setFirstname("Amit");
		input.setLastname("Sharma");
		input.setPassword("1234");
		input.setUsername("amits");
		input.setUserId(5);
		
		doNothing().when(entityManager).persist(input);
		Integer actual = userDao.addUser(input);
		assertEquals(input.getUserId(), actual);
		verify(entityManager).persist(input);
		
	}
	
	@Test
	public void shouldLoginAndReturnUser(){
		
		List<User> users = new ArrayList<User>();
		User input = new User();
		input.setFirstname("Amit");
		input.setLastname("Sharma");
		input.setPassword("1234");
		input.setUsername("1234");
		input.setUserId(1);
		users.add(input);
		
		when(entityManager.createQuery(anyString())).thenReturn(query);
		when(entityManager.createQuery(anyString()).getResultList()).thenReturn(users);
		
		User actual = userDao.validate(input.getUsername(), input.getPassword());
		assertEquals(input, actual);
		verify(entityManager,times(2)).createQuery(anyString());
		
	}
	
	
	@Test
	public void shouldLoginAndReturnNull(){
		
		List<User> users = new ArrayList<User>();
		User input = new User();
		input.setPassword("1234");
		input.setUsername("1234");
		
		when(entityManager.createQuery(anyString())).thenReturn(query);
		when(entityManager.createQuery(anyString()).getResultList()).thenReturn(users);
		
		User actual = userDao.validate(input.getUsername(), input.getPassword());
		assertNull(actual);
		verify(entityManager,times(2)).createQuery(anyString());
		 
	}
	
}
