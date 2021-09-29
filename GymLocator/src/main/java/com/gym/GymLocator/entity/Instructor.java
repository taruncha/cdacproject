package com.gym.GymLocator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Instructor")
public class Instructor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private int gymid;
	@Column
	private String trainername;
	@Column
	private String Experience;
	@Column
	private String Gender;

	public Instructor(int gymid, String trainername, String experience, String gender) {
		super();
		this.id = id;
		this.gymid = gymid;
		this.trainername = trainername;
		Experience = experience;
		Gender = gender;
	}

	public Instructor() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGymid() {
		return gymid;
	}

	public void setGymid(int gymid) {
		this.gymid = gymid;
	}

	public String getTrainername() {
		return trainername;
	}

	public void setTrainername(String trainername) {
		this.trainername = trainername;
	}

	public String getExperience() {
		return Experience;
	}

	public void setExperience(String experience) {
		Experience = experience;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", gymid=" + gymid + ", trainername=" + trainername + ", Experience="
				+ Experience + ", Gender=" + Gender + "]";
	}

}
