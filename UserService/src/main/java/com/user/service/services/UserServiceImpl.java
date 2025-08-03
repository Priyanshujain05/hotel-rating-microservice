package com.user.service.services;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.service.entities.Hotel;
import com.user.service.entities.Rating;
import com.user.service.entities.User;
import com.user.service.exceptions.UserNotFoundException;
import com.user.service.externalServices.HotelClientService;
import com.user.service.externalServices.RatingClientService;
import com.user.service.logging.LoggerUtil;
import com.user.service.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository uRepo;
	@Autowired
	private RatingClientService rcs;
	@Autowired
	private HotelClientService hcs;
	private static final Logger logger = LoggerUtil.getLogger("UserLogger");

	public UserServiceImpl(UserRepository uRepo) {
		super();
		this.uRepo = uRepo;
	}

	@Override
	public User createUser(User user) {
		String uuid = UUID.randomUUID().toString();
		user.setUserId(uuid);
		List<Rating> ratings = user.getRatings();
		ratings.stream().forEach(r->{
			Rating rating = rcs.createRating(r).getBody();
		});
		user.setRatings(ratings);
		return uRepo.save(user);
	}

	@Override
	public User getUser(String userId) {
		User user = uRepo.findById(userId).orElseThrow(() -> new UserNotFoundException(""));
		// find ratings given by user
		List<Rating> ratingsOfUser = rcs.getData(userId);

		ratingsOfUser.stream().forEach(r -> {
			try {
				Hotel hotelRatedByUser = hcs.getData(r.getHotelId());
				r.setHotel(hotelRatedByUser);
				logger.info("Hotel details fetched successfully for rating with rating id: " + r.getRatingId());
			} catch (RuntimeException e) {
				logger.info(e.getLocalizedMessage());
			}
		});
		user.setRatings(ratingsOfUser);
		logger.info("Ratings fetched successfully for user: " + user.getUserName());
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		return uRepo.findAll();
	}

	@Override
	public boolean deleteUser(User user) {
		if (uRepo.findById(user.getUserId()) != null) {
			uRepo.deleteById(user.getUserId());
		}
		return !uRepo.existsById(user.getUserId());
	}

	@Override
	public User updateUser(User user) {
		User u = uRepo.findById(user.getUserId()).orElseThrow(() -> new UserNotFoundException(""));
		u.setAbout(user.getAbout());
		u.setEmail(user.getEmail());
		u.setUserName(user.getUserName());
		uRepo.save(u);
		return u;
	}

}
