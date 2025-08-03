package com.rating.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user_ratings")
public class Rating {

	@Id
	private String ratingId;
	private String hotelId;
	private String userId;
	private String rating;
	private String feedback;
	private double stars;

}
