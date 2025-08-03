package com.rating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rating.entities.Rating;

@Repository
public interface RatingRepo extends JpaRepository<Rating, String>{
	
	List<Rating> findRatingByUserId(String userId);
	
	List<Rating> findRatingByHotelId(String hotelId);


}
