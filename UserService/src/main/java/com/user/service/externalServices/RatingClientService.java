package com.user.service.externalServices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.user.service.entities.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingClientService {

	@GetMapping("/ratings/users/{id}")
	List<Rating> getData(@RequestParam("id") String id);

	@PostMapping("/ratings")
	ResponseEntity<Rating> createRating(Rating rating);
}
