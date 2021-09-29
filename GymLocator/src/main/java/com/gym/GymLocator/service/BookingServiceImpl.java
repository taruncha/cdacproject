package com.gym.GymLocator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gym.GymLocator.dao.BookingRepository;
import com.gym.GymLocator.entity.Booking;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public void bookgym(Integer id, int userId,String membership) {

		Booking book = new Booking(userId, id,membership);
		bookingRepository.save(book);

	}

	@Override
	public int cancelBooking(int userId, Integer id) {
		int a = id;
		bookingRepository.cancelBook(userId, a);

		return 1;

	}

}
