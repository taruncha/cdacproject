package com.gym.GymLocator.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gym.GymLocator.entity.Booking;
import com.gym.GymLocator.entity.Gymregister;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

	@Query("from Booking b where b.uid=:bid")
	List<Booking> getBookingId(@Param("bid") int userId);

	@Modifying
	@Transactional
	@Query(" delete from Booking b where b.uid=:bid and b.gid=:kid")
	void cancelBook(@Param("bid") int userId, @Param("kid") int a);

	@Query("from Booking b where b.gid=:gymid")
	List<Booking> getBookingIdList(@Param("gymid") int id);

}
