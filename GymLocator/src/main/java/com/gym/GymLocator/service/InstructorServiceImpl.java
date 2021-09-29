package com.gym.GymLocator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gym.GymLocator.dao.InstructorRepository;
import com.gym.GymLocator.entity.Instructor;

@Service
public class InstructorServiceImpl implements InstructorService {

	@Autowired
	private InstructorRepository instructorRepository;

	@Override
	public void UpdateIns(String trainername, String experience, String gender, int id) {
//		String gymid =Integer.toString(id); 
		Instructor i = new Instructor(id, trainername, experience, gender);
		instructorRepository.save(i);
		
	}

	@Override
	public List<Instructor> getTrainer(int id) {
		System.out.println("id callsed = "+id);
		List<Instructor> l = instructorRepository.getTrainer(id);
		
		return l;
	}

	@Override
	public void deleteTrainer(Integer id) {
		instructorRepository.delete(id);
		
	}

	@Override
	public List<Instructor> getInstructor(int theID) {
		List<Instructor> l=instructorRepository.getIns(theID);
		return l;
	}

	@Override
	public Instructor getInstructor(String name, String exp, String gen) {
		Instructor i =instructorRepository.get(name, exp,gen);
		return i;
	}
	
}
