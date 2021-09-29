package com.gym.GymLocator.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gym.GymLocator.entity.Gymregister;
import com.gym.GymLocator.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("from User u where u.username=:uname and u.password=:pwd")
	User loginCustomer(@Param("uname") String username, @Param("pwd") String password);

	@Query("from User u where u.username=:uname and u.password=:pwd")
	User getuser(@Param("uname") String username, @Param("pwd") String password);

	@Query("from User u where u.id=:bid")
	User getBookingID(@Param("bid") int userId);

	@Query("from User u where u.id=:bookid")
	User getUserList(@Param("bookid") int uid);

	@Query("from User u where u.id=:uid")
	User getLoginUser(@Param("uid") int userId);

}
