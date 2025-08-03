package com.rating.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rating.entities.Rating;
import com.rating.services.RatingServiceImpl;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	private RatingServiceImpl rImpl;

	public RatingController(RatingServiceImpl rImpl) {
		super();
		this.rImpl = rImpl;
	}

	@PostMapping()
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
		return ResponseEntity.status(HttpStatus.CREATED).body(rImpl.addRating(rating));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Rating> getRating(@PathVariable("id") String ratingId) {
		return ResponseEntity.status(HttpStatus.OK).body(rImpl.getRating(ratingId));
	}

	@GetMapping()
	public ResponseEntity<List<Rating>> getAllRating() {
		return ResponseEntity.status(HttpStatus.OK).body(rImpl.getAllRatings());
	}

	@PutMapping
	public ResponseEntity<Rating> updateRating(@RequestBody Rating rating) {
		return ResponseEntity.status(HttpStatus.OK).body(rImpl.updateRating(rating));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Rating> deleteRating(@PathVariable("id") String ratingId) {
		return ResponseEntity.status(HttpStatus.OK).body(rImpl.deleteRating(ratingId));
	}
	
	@GetMapping("users/{id}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable("id") String userId) {
		return ResponseEntity.status(HttpStatus.OK).body(rImpl.getRatingByUserId(userId));
	}
	
	@GetMapping("hotels/{id}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable("id") String hotelId) {
		return ResponseEntity.status(HttpStatus.OK).body(rImpl.getRatingByHotelId(hotelId));
	}
}
