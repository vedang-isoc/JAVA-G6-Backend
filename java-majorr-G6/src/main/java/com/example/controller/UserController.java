package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Course;
import com.example.entity.Profile;
import com.example.entity.User;
import com.example.entity.Video;
import com.example.repositiories.UserRepo;
import com.example.service.UserService;

@RestController

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")

public class UserController {
	
	 @Autowired
	 private UserRepo ur;
	
	@Autowired
	UserService uservice;
	
	@PutMapping(path="/{cid}")
	public String like(@PathVariable int cid) {
		
		//11 is the user id
		boolean status=uservice.like(4, cid);
		if(status) {
			return "Liked";
		}
		return "can not like";
	}
	@DeleteMapping(path="/{likeid}/{cid}")
	public String unlike(@PathVariable int likeid,@PathVariable int cid) {
		
		//11 is the user id
		boolean status=uservice.unlike(likeid,cid);
		if(status) {
			return "Un Liked";
		}
		return "can not unlike";	}
	@GetMapping(path="/courses")
	public List<Course> getcourses(){
		return uservice.getCourses();
	}
	@GetMapping(path="/isCourseCompleted/{cid}")
	public boolean test(@PathVariable int cid) {
		return uservice.isCourseCompleted(4,cid);
		
	}
	@GetMapping(path="/enrolledcourses")
	public List<Course> getecourses(){
		return uservice.getEnrolledCourse(4);
	}
	@GetMapping(path="/enrolledcourses/video/{cid}/{uid}")
	public List<Video> getVideo(@PathVariable int cid,@PathVariable int uid){
		return uservice.getEnrolledCourseVideo(uid,cid);
	}
	@GetMapping(path="/lockedusers")
	public List<User> getLocked(){
		return uservice.getLockedAccount();
	}
	@PutMapping(path="/unlockuser")
	public boolean unlock(){
		return uservice.unlocakAccount(4); 
	}
	@PutMapping(path="/lockuser")
	public boolean lock(){
		return uservice.lockAccount(4);
	}
	@GetMapping(path="/generatePdf/{cid}")
	public boolean generatepdf(@PathVariable int cid){
		return uservice.generateCompeletionCerti(100, cid);
	}
	@PutMapping(path="enroll/{cid}/{uid}")
	public String enroll(@PathVariable int cid,@PathVariable int uid) {
		
		//11 is the user id
		boolean status=uservice.Enroll(cid, uid);
		if(status) {
			return "Enrolled";
		}
		return "can not enroll";
	}
	
	@GetMapping(path="nextvideo/{cid}/{uid}/{vid}")
	public boolean nextVideo(@PathVariable int cid,@PathVariable int uid,@PathVariable int vid) {
		
		
		return uservice.nextVideo(cid, uid, vid);
	}
	@PutMapping(path="completevideo/{cid}/{uid}/{vid}")
	public boolean completeVideo(@PathVariable int cid,@PathVariable int uid,@PathVariable int vid) {
		
		
		return uservice.completeVideo(cid, uid, vid);
	}
	
	@PostMapping(path="/adduser")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String createUser(@RequestBody User user) {
		return uservice.createUser(user);
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.FOUND)
	public User forLogin(@RequestBody User user) {
		return uservice.forLogin(user.getUsername(), user.getEmail(), user.getPassword());
	}
	
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.FOUND)
	public Optional<User> getuserDetails(@PathVariable int id) {
		return uservice.getuserDetails(id);
	}
	
	@GetMapping("/{id}/{password}")
	@ResponseStatus(code = HttpStatus.FOUND)
	public String getPassword(@PathVariable int id,@PathVariable String password) {
		return uservice.getPassword(id, password);
	}
	
	@PutMapping("/update")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String editProfile(@RequestBody User user) {
		return uservice.editUserDetails(user);
	}
	
	@PutMapping("/password") // we will take whole object of user first in UI then will send the updated object 
	public User changePassword(@RequestBody User user) {
		return uservice.changePassword(user);
	}
	
	@GetMapping("/email/{email}")
	public String getEmailSendTempPass(@PathVariable String email) {
		return uservice.getEmailSendTempPass(email);
	}
	
	@GetMapping("/username/{username}")
	public String checkUsername(@PathVariable String username) {
		return uservice.checkUsername(username);
	}
	
	@PostMapping("/profile/{userid}") //we have send userid while creating profile
	@ResponseStatus(code = HttpStatus.CREATED)
	public Profile createProfile(@PathVariable int userid,@RequestBody Profile profile) {
		return uservice.createProfile(userid, profile);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable int id) {
		return uservice.deleteUser(id);
	}
	
	@GetMapping("/profile/{userid}")
	public Profile getProfileDetails(@PathVariable int userid) {
		return uservice.getProfileDetails(userid);
	}
	
	@PutMapping("/profile/{userid}")
	public Profile editProfile(@PathVariable int userid, @RequestBody Profile profile) {
		return uservice.editProfile(userid, profile);
	}
	  @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	    @RequestMapping(value="/users", method = RequestMethod.GET)
	    public List<User> listUser(){
	        return ur.findAll();
	    }

	    @PreAuthorize("hasRole('USER')")
	    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	    public Optional<User> getOne(@PathVariable(value = "id") int id){
	        return ur.findById(id);
	    }

	    @RequestMapping(value="/signup", method = RequestMethod.POST)
	    public User saveUser(@RequestBody User user){
	    	
	    	user.setRole("USER");
	        return ur.save(user);
	    }

	

}
