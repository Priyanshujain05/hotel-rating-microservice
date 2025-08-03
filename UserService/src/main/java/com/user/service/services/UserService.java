package com.user.service.services;

import java.util.List;

import com.user.service.entities.User;

public interface UserService {

	User createUser(User user);

	User getUser(String userId);

	List<User> getAllUsers();

	boolean deleteUser(User user);

	User updateUser(User user);

}
