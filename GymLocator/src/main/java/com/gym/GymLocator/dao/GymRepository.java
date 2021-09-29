package com.gym.GymLocator.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gym.GymLocator.entity.Gymregister;

public interface GymRepository extends JpaRepository<Gymregister, Integer> {

	@Query("from Gymregister u where u.uname=:username and u.pwd=:password")
	Gymregister loginGym(@Param("username") String username, @Param("password") String password);

	@Query("from Gymregister u where u.city=:cityname or u.state=:cityname or u.country=:cityname or u.pincode=:cityname and u.price!=0 and u.gymname!=null")
	List<Gymregister> searchCity(@Param("cityname") String city);

	@Query("from Gymregister where price!=0 and gymname!=null order by price")
	List<Gymregister> searchLow();

	@Query("from Gymregister where price!=0 and gymname!=null order by price desc")
	List<Gymregister> searchHigh();

	@Query("from Gymregister where price!=0 and gymname!=null order by country")
	List<Gymregister> searchCountry();

	@Query("from Gymregister where price!=0 and gymname!=null order by gymname")
	List<Gymregister> searchGymName();

	@Query("from Gymregister u where u.id=:uid")
	List<Gymregister> getByUserId(@Param("uid") int userId);

	@Query("from Gymregister u where u.city=:cityname or u.state=:cityname or u.country=:cityname or u.pincode=:cityname")
	List<Gymregister> locateCity(@Param("cityname") String loccity);

	@Query("from Gymregister u where u.id=:gid")
	List<Gymregister> findGymById(@Param("gid") int theID);

	@Query("from Gymregister u where u.id=:kid")
	Gymregister getBookGym(@Param("kid") int gid);

	@Query("from Gymregister where price!=0 and gymname!=null")
	List<Gymregister> findGyms();

	@Query("from Gymregister u where u.uname=:username and u.pwd=:password")
	List<Gymregister> getGym(@Param("username") String username, @Param("password") String pass);

	@Modifying
	@Transactional
	@Query(" update Gymregister g set g.addressline=:add ,g.city=:cty,g.state=:stat,g.country=:cntry,g.pincode=:pin where g.uname=:usm and g.pwd=:pass")
	void updateAdd(@Param("add") String addressline, @Param("cty") String city, @Param("stat") String state,@Param("cntry") String country, @Param("pin") String pincode, @Param("usm") String username,@Param("pass") String pass);

	@Modifying
	@Transactional
	@Query(" update Gymregister g set g.contact=:con where g.uname=:usm and g.pwd=:pass")
	void updatecontact(@Param("con") String contact, @Param("usm") String username, @Param("pass") String pass);

//	@Modifying
//	@Transactional
//	@Query(" update Gymregister g set g.price=:pric,g.offer=:off where g.uname=:usm and g.pwd=:pass")
//	void updatePrice(@Param("pric") double price, @Param("off") String offer, @Param("usm") String username,@Param("pass") String pass);
	
	
	@Modifying
	@Transactional
	@Query(" update Gymregister g set g.price=:pric,g.offer=:off,g.offermonth=:offmonth,g.offeryear=:offyear where g.uname=:usm and g.pwd=:pass")
	void updatePrice(@Param("pric") double price, @Param("off") String offer,@Param("offmonth") String offermonth,@Param("offyear") String offeryear,@Param("usm") String username,@Param("pass") String pass);

	//	void updatePrice(double price, String offer,  String username, String pass);
//	@Modifying
//	@Transactional
//	@Query(" update Gymregister g set g.insname=:iname,g.experience=:exp where g.uname=:usm and g.pwd=:pass")
//	void updateInstructor(@Param("iname") String insname, @Param("exp") String experience,@Param("usm") String username, @Param("pass") String pass);

	@Modifying
	@Transactional
	@Query(" update Gymregister g set g.aadhar=:adh where g.uname=:usm and g.pwd=:pass")
	void updateDetail(@Param("adh") String adhar, @Param("usm") String username, @Param("pass") String pass);

	@Modifying
	@Transactional
	@Query(" update Gymregister g set g.gymname=:gname where g.uname=:usm and g.pwd=:pass")
	void updateName(@Param("gname") String name, @Param("usm") String username, @Param("pass") String pass);

	@Query("from Gymregister g where g.uname=:usm and g.pwd=:pass")
	Gymregister getGymId(@Param("usm") String username, @Param("pass") String pass);

	

}
