package com.gym.GymLocator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private String fname;
	@Column
	private String lname;
	@Column
	private String email;
	@Column
	private String phno;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String address;
	@Column
	private String gen;

//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//	List<Gymregister> gym;
//
//	@ManyToOne
//	Gymregister gymregister;

//	@ManyToMany
//	private List<Gymregister> gyms;

	public User() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGen() {
		return gen;
	}

	public void setGen(String gen) {
		this.gen = gen;
	}

//	public void add(Gymregister gym_name) {
//		gym.add(gym_name);
//		gym_name.setUser(this);
//		System.out.println("add method called =,this = " + this);
//
//	}
//
//	public List<Gymregister> getGym() {
//		return gym;
//	}
//
//	public Gymregister getGymregister() {
//		return gymregister;
//	}
//
//	public void setGymregister(Gymregister gymregister) {
//		this.gymregister = gymregister;
//	}
//
//	public void setGym(List<Gymregister> gym) {
//		this.gym = gym;
//	}

	@Override
	public String toString() {
		return "User [fname=" + fname + ", lname=" + lname + ", email=" + email + ", phno=" + phno + ", username="
				+ username + ", password=" + password + ", address=" + address + ", gen=" + gen + "]";
	}

}
