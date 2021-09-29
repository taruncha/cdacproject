package com.gym.GymLocator.service;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gym.GymLocator.dao.BookingRepository;
import com.gym.GymLocator.dao.UserRepository;
import com.gym.GymLocator.entity.Booking;
import com.gym.GymLocator.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookingRepository bookingRepository;

	User u;

	@Override
	@Transactional
	public void saveUser(User user) {
		userRepository.save(user);

	}

	@Override
	public List<Booking> getBookingID(int userId) {
		List<Booking> b = bookingRepository.getBookingId(userId);
		System.out.println("booking= " + b);
		return b;
	}

	@Override
	public User getUserList(int uid) {
		User u= userRepository.getUserList(uid);
		System.out.println("bookeeed gym = " + u);
		return u;
	}

	@Override
	public User getLoginUser(int userId) {
		User u = userRepository.getLoginUser(userId);
		return u;
	}

}
