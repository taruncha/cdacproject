package com.gym.GymLocator.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gym.GymLocator.entity.Instructor;


public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

	@Query("from Instructor i where gymid=:insid")
	List<Instructor> getTrainer(@Param("insid") int id);

	@Modifying
	@Transactional
	@Query("delete from Instructor i where id=:delid")
	void delete(@Param("delid") Integer id);

	@Query("from Instructor where gymid=:gid")
	List<Instructor> getIns(@Param("gid") int theID);

	@Query("from Instructor where trainername=:nam and gender=:gendr and experience=:expe")
	Instructor get(@Param("nam") String name,@Param("expe")  String exp,@Param("gendr")  String gen);

}
