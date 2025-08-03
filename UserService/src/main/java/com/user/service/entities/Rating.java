package com.user.service.entities;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="ratings")
public class Rating {

	
	private String ratingId;
	private String userId;
	
	private String hotelId;
	private String rating;
	private String feedback;
	private Hotel hotel;
	
}
