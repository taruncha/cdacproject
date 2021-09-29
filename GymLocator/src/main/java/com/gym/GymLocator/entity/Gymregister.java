package com.gym.GymLocator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Gymregister")
public class Gymregister {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private String fsname;
	@Column
	private String lsname;
	@Column
	private String gymemail;
	@Column
	private String gymname;
	@Column
	private String uname;
	@Column
	private String pwd;
	@Column
	private String contact;
	@Column
	private double price;
	@Column
	private String addressline;
	@Column
	private String city;
	@Column
	private String state;
	@Column
	private String country;
	@Column
	private String pincode;
	@Column
	private String aadhar;
	@Column
	private String offer;
	
	@Column
	private String offermonth;
	
	@Column
	private String offeryear;
	
	private String member;

//	@ManyToOne(cascade = CascadeType.ALL)
//	User user;
//
//	@OneToMany(mappedBy = "gymregister")
//	List<User> users;

	public Gymregister() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFsname() {
		return fsname;
	}

	public void setFsname(String fsname) {
		this.fsname = fsname;
	}

	public String getLsname() {
		return lsname;
	}

	public void setLsname(String lsname) {
		this.lsname = lsname;
	}

	public String getGymemail() {
		return gymemail;
	}

	public void setGymemail(String gymemail) {
		this.gymemail = gymemail;
	}

	public String getGymname() {
		return gymname;
	}

	public void setGymname(String gymname) {
		this.gymname = gymname;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAddressline() {
		return addressline;
	}

	public void setAddressline(String addressline) {
		this.addressline = addressline;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public String getOffermonth() {
		return offermonth;
	}

	public void setOffermonth(String offermonth) {
		this.offermonth = offermonth;
	}

	

	public String getOfferyear() {
		return offeryear;
	}

	public void setOfferyear(String offeryear) {
		this.offeryear = offeryear;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "Gymregister [id=" + id + ", fsname=" + fsname + ", lsname=" + lsname + ", gymemail=" + gymemail
				+ ", gymname=" + gymname + ", uname=" + uname + ", pwd=" + pwd + ", contact=" + contact + ", price="
				+ price + ", addressline=" + addressline + ", city=" + city + ", state=" + state + ", country="
				+ country + ", pincode=" + pincode + ", aadhar=" + aadhar + ", offer=" + offer + "]";
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	public List<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}
//	
//	
//	public void adding(User user_name) {
//		users.add(user_name);
//		user_name.setGymregister(this);
//		System.out.println("add method called =,this = " + this);
//	}
//

//	public void add(Gymregister gym_name) {
//		gym.add(gym_name);
//		gym_name.setUser(this);
//		System.out.println("add method called =,this = " + this);
//
//	}

}
