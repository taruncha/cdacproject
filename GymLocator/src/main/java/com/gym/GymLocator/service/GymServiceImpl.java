package com.gym.GymLocator.service;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.GymLocator.controller.gymController;
import com.gym.GymLocator.dao.BookingRepository;
import com.gym.GymLocator.dao.GymRepository;
import com.gym.GymLocator.dao.UserRepository;
import com.gym.GymLocator.entity.Booking;
import com.gym.GymLocator.entity.Gymregister;
import com.gym.GymLocator.entity.User;

@Service
public class GymServiceImpl implements GymService {

	@Autowired
	private GymRepository gymRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookingRepository bookingRepository;

	User u;

	@Override
	@Transactional
	public void saveGym(Gymregister theGymRegister) {
		gymRepository.save(theGymRegister);

	}

	@Override
	@Transactional
	public boolean gymLogin(String uname, String pwd) {
		Gymregister g = gymRepository.loginGym(uname, pwd);
		if (g != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public List<Gymregister> findAll() {
		// return gymRepository.findAll();

		List<Gymregister> l = gymRepository.findGyms();

		return l;
	}

	@Override
	@Transactional
	public List<Gymregister> findByCity(String city) {
		List<Gymregister> l = gymRepository.searchCity(city);
		return l;
	}

//	@Override
//	public void getGym(int theID) {
//
//		Gymregister g = gymRepository.getById(theID);
//		System.out.println("g= "+g);
//		User user = userRepository.getById(u.getId());
//		g.adding(user);
//		userRepository.save(user);
//
//	}

	@Override
	public boolean userLogin(String username, String password) {
		u = userRepository.loginCustomer(username, password);

		if (u != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Gymregister> findByLow() {
		List<Gymregister> l = gymRepository.searchLow();
		return l;
	}

	@Override
	public List<Gymregister> findByhigh() {
		List<Gymregister> l = gymRepository.searchHigh();
		return l;
	}

	@Override
	public List<Gymregister> findByCountry() {
		List<Gymregister> l = gymRepository.searchCountry();
		return l;
	}

	@Override
	public List<Gymregister> findByGymName() {
		List<Gymregister> l = gymRepository.searchGymName();
		return l;
	}

	@Override
	public int getUserId(String username, String password) {
		int usId;
		User us = new User();
		us = userRepository.getuser(username, password);
		usId = us.getId();
		System.out.println("usid = " + usId);
		return usId;
	}

	@Override
	public List<Gymregister> findByUserID(int userId) {
		List<Gymregister> gd = gymRepository.getByUserId(userId);
		return gd;
	}

	@Override
	public List<Gymregister> locateByCity(String loccity) {
		List<Gymregister> loc = gymRepository.locateCity(loccity);
		return loc;
	}

	@Override
	public List<Gymregister> getBookingGym(int theID) {
		List<Gymregister> l = gymRepository.findGymById(theID);
		return l;
	}
//-------------booking----------//

	@Override
	public Gymregister getBookedGym(int gid) {
		Gymregister g = gymRepository.getBookGym(gid);
		System.out.println("bookeeed gym = " + g);
		return g;
	}
	// -------------booking end----------//

	@Override
	public List<Gymregister> getGym(String username, String pass) {
		List<Gymregister> gym = gymRepository.getGym(username, pass);
		return gym;
	}

	// -----------------update details-------------//

	@Override
	public void updateAddress(Gymregister gymUpdate, String username, String pass) {

		gymRepository.updateAdd(gymUpdate.getAddressline(), gymUpdate.getCity(), gymUpdate.getState(),
				gymUpdate.getCountry(), gymUpdate.getPincode(), username, pass);

	}

	@Override
	public void updateContact(String contact, String username, String pass) {

		gymRepository.updatecontact(contact, username, pass);

	}

	@Override
	public void updatePrice(double price, String offer, String offermonth, String offeryear, String username,
			String pass) {
		gymRepository.updatePrice(price, offer,offermonth,offeryear, username, pass);

	}

//	@Override
//
//	public void updatePrice(double price, String offer, String username, String pass) {
//
//		gymRepository.updatePrice(price, offer, username, pass);
//
//	}

//	@Override
//	public void updateInstructor(String insname, String experience, String username, String pass) {
//		
//		gymRepository.updateInstructor(insname, experience, username, pass);
//		
//	}

	@Override
	public void updateDetail(String adhar, String username, String pass) {

		gymRepository.updateDetail(adhar, username, pass);

	}

	@Override
	public void updateGymName(String name, String username, String pass) {
		// TODO Auto-generated method stub
		gymRepository.updateName(name, username, pass);
	}

//----------booking detauils-------//
	@Override
	public int getGYmID(String username, String pass) {
		Gymregister g = gymRepository.getGymId(username, pass);
		System.out.println(g.getId());
		int id = g.getId();

		return id;
	}

	@Override
	public List<Booking> getGymIDList(int id) {

		List<Booking> l = bookingRepository.getBookingIdList(id);
		System.out.println(" before iterated");

		return l;
	}

}
