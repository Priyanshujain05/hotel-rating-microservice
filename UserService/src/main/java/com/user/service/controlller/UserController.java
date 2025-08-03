package com.user.service.controlller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.entities.User;
import com.user.service.services.UserServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserServiceImpl userServiceImpl;

	public UserController(UserServiceImpl userServiceImpl) {
		super();
		this.userServiceImpl = userServiceImpl;
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User createdUser = userServiceImpl.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	@GetMapping("/{userId}")
	@CircuitBreaker(name="ratingHotelBreaker", fallbackMethod = "ratinghotelFallback")
	public  ResponseEntity<User>  getUser(@PathVariable("userId") String userId) {
		User user = userServiceImpl.getUser(userId);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	public ResponseEntity<User> ratinghotelFallback(String userId, Exception e){
		User user = User.builder().userName("Dummy")
				.email("dummy@dummy.com")
				.about("This is dummy user")
				.build();
		return new ResponseEntity(user, HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<List<User>> getAllUser() {
		List<User> allUsers = userServiceImpl.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(allUsers);
	}
	
	@DeleteMapping
	public ResponseEntity<Boolean> deleteUser(@RequestBody User user) {
		boolean isUserDeleted = userServiceImpl.deleteUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(isUserDeleted);
	}
	
	@PutMapping
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		User updatedUser = userServiceImpl.updateUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
	}
}
