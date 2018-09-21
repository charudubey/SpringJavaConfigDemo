package com.yash.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.dao.UserDao;
import com.yash.model.User;

@Service(value="userService")
public class UserService {

	@Autowired
	UserDao userDao;
	
	@Transactional
	public Integer register(User user){
		return userDao.addUser(user);
	}
	
	public User validateUser(String username, String password){
		return userDao.validate(username, password);
	}


}
