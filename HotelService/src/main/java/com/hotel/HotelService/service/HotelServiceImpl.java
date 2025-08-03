package com.hotel.HotelService.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hotel.HotelService.entities.Hotel;
import com.hotel.HotelService.exceptions.ResourceNotFoundException;
import com.hotel.HotelService.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {

	private HotelRepository hotelRepository;

	public HotelServiceImpl(HotelRepository hotelRepository) {
		super();
		this.hotelRepository = hotelRepository;
	}

	@Override
	public Hotel addHotel(Hotel hotel) {
		String uuid = UUID.randomUUID().toString();
		hotel.setId(uuid);
		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel getHotel(String hotelId) {
		return hotelRepository.findById(hotelId)
				.orElseThrow(() -> new ResourceNotFoundException("hotel not found with id:" + hotelId));
	}

	@Override
	public List<Hotel> getAllHotels() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel updateHotel(Hotel hotel) {
		Hotel h = hotelRepository.findById(hotel.getId()).get();
		h.setName(hotel.getName());
		h.setLocation(hotel.getLocation());
		h.setAbout(hotel.getAbout());
		hotelRepository.save(h);
		return h;
	}

	@Override
	public Hotel deleteHotel(String hotelId) {
		if (hotelRepository.existsById(hotelId)) {
			Hotel hotel = hotelRepository.findById(hotelId).get();
			hotelRepository.deleteById(hotelId);
			return hotel;
		} else {
			throw new ResourceNotFoundException("Hotel with id: " + hotelId + " dosen't exists in database");
		}

	}

}
