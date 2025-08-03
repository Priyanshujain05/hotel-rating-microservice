package com.hotel.HotelService.service;

import java.util.List;

import com.hotel.HotelService.entities.Hotel;

public interface HotelService {
	
	Hotel addHotel(Hotel hotel);
	
	Hotel getHotel(String hotelId);
	
	List<Hotel> getAllHotels();
	
	Hotel updateHotel(Hotel hotel);
	
	Hotel deleteHotel(String hotelId);

}
