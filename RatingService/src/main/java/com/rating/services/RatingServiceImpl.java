package com.rating.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rating.entities.Rating;
import com.rating.exceptions.ResourceNotFoundException;
import com.rating.repository.RatingRepo;

@Service
public class RatingServiceImpl implements RatingService {

	private RatingRepo repo;

	public RatingServiceImpl(RatingRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Rating addRating(Rating rating) {
		String id = UUID.randomUUID().toString();
		rating.setRatingId(id);
		return repo.save(rating);
	}

	@Override
	public Rating getRating(String ratingId) {
		return repo.findById(ratingId).orElseThrow(
				() -> new ResourceNotFoundException("Rating for id:" + ratingId + " not found in database."));
	}

	@Override
	public List<Rating> getAllRatings() {
		return repo.findAll();
	}

	@Override
	public Rating updateRating(Rating rating) {
		if (repo.existsById(rating.getRatingId())) {
			Rating r = repo.findById(rating.getRatingId()).get();
			r.setFeedback(rating.getFeedback());
			r.setHotelId(rating.getHotelId());
			r.setUserId(rating.getUserId());
			r.setRating(rating.getRating());
			r.setStars(rating.getStars());
			return repo.save(r);
		} else {
			throw new ResourceNotFoundException("Rating for id:" + rating.getRatingId() + " not found in database.");
		}
	}

	@Override
	public Rating deleteRating(String ratingId) {
		if (repo.existsById(ratingId)) {
			Rating r = repo.findById(ratingId).get();
			repo.deleteById(ratingId);
			return r;
		} else {
			throw new ResourceNotFoundException("Rating for id:" + ratingId + " not found in database.");

		}

	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		return repo.findRatingByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		return repo.findRatingByHotelId(hotelId);
	}
}