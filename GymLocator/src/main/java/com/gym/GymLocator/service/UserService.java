package com.gym.GymLocator.service;

import java.util.List;
import com.gym.GymLocator.entity.Booking;
import com.gym.GymLocator.entity.User;

public interface UserService {

	public void saveUser(User user);

	public List<Booking> getBookingID(int userId);

	public User getUserList(int uid);

	public User getLoginUser(int userId);

}
