package com.gym.GymLocator.service;

public interface BookingService {

	void bookgym(Integer id, int userId,String membership);

	int cancelBooking(int userId, Integer id);

}
