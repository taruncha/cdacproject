package com.gym.GymLocator.service;

import java.util.List;

import com.gym.GymLocator.entity.Instructor;

public interface InstructorService {

	void UpdateIns(String trainername, String experience, String gender, int id);

	List<Instructor> getTrainer(int id);

	void deleteTrainer(Integer id);

	List<Instructor> getInstructor(int theID);

	Instructor getInstructor(String name, String exp, String gen);

}
