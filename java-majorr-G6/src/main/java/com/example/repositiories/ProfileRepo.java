package com.example.repositiories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Profile;
import com.example.entity.User;

public interface ProfileRepo extends JpaRepository<Profile, Integer>{
	public Profile findByUser(User u);
	
	

	@Query(value = "select * from profile p where p.user_id=?1", nativeQuery = true)
	public Profile findProfile(int userid);

	
}
