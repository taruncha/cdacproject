package com.gym.GymLocator.service;

import java.util.List;

import com.gym.GymLocator.entity.Booking;
import com.gym.GymLocator.entity.Gymregister;

public interface GymService {

	public void saveGym(Gymregister theGymRegister);

	public boolean gymLogin(String uname, String pwd);

	public List<Gymregister> findAll();

	public List<Gymregister> findByCity(String city);

	public boolean userLogin(String username, String password);

	public List<Gymregister> findByLow();

	public List<Gymregister> findByhigh();

	public List<Gymregister> findByCountry();

	public List<Gymregister> findByGymName();

	public int getUserId(String username, String password);

	public List<Gymregister> findByUserID(int userId);

	public List<Gymregister> locateByCity(String city);

	public List<Gymregister> getBookingGym(int theID);

	public Gymregister getBookedGym(int gid);

	public List<Gymregister> getGym(String username, String pass);

	public void updateAddress(Gymregister gymUpdate, String username, String pass);

	public void updateContact(String contact, String username, String pass);

	//public void updateInstructor(String insname, String experience, String username, String pass);

	public void updateDetail(String adhar, String username, String pass);

	public void updateGymName(String name, String username, String pass);

	public int getGYmID(String username, String pass);

	public List<Booking> getGymIDList(int id);

	public void updatePrice(double price, String offer, String offermonth, String offeryear, String username, String pass);


}
