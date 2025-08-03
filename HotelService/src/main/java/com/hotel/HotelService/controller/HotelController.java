package com.hotel.HotelService.controller;

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

import com.hotel.HotelService.entities.Hotel;
import com.hotel.HotelService.service.HotelServiceImpl;

@RestController
@RequestMapping("hotel")
public class HotelController {

	private HotelServiceImpl hotelService;

	public HotelController(HotelServiceImpl hotelService) {
		super();
		this.hotelService = hotelService;
	}

	@PostMapping()
	public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) {
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.addHotel(hotel));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Hotel> getHotel(@PathVariable("id") String hotelId) {
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotel(hotelId));
	}

	@GetMapping()
	public ResponseEntity<List<Hotel>> getHotel() {
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAllHotels());
	}

	@PutMapping()
	public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel) {
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.updateHotel(hotel));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Hotel> deleteHotel(@PathVariable String hotelId) {
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.deleteHotel(hotelId));
	}
}
