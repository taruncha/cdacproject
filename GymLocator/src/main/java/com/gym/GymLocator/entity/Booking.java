package com.gym.GymLocator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Booking")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private int uid;
	@Column
	private int gid;
	@Column
	private String membership;

	public Booking() {
		super();
	}

	

	public String getMembership() {
		return membership;
	}



	public void setMembership(String membership) {
		this.membership = membership;
	}



	public Booking(int uid, int gid, String membership) {
		super();
		this.id = id;
		this.uid = uid;
		this.gid = gid;
		this.membership = membership;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", uid=" + uid + ", gid=" + gid + "]";
	}

}
