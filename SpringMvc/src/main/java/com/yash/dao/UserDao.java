package com.yash.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.yash.model.User;

@Repository
public class UserDao {
	
	@PersistenceContext
	EntityManager entityManager;

	public Integer addUser(User user) {
		entityManager.persist(user);
		return user.getUserId();
	}

	public User validate(String username, String password) {
		List<User> users = entityManager.createQuery(
				"from User where username='"+username+"' and password='"+password+"'")
				.getResultList();
		return users.size()>0 ? users.get(0) : null;
	}

}
