package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.entity.Course;
import com.example.entity.Profile;
import com.example.entity.User;
import com.example.entity.Video;

public interface UserService {
	public boolean like(int uid,int cid);
	public boolean unlike(int likeid,int cid);
	public List<Course> getCourses();
	public boolean isCourseCompleted(int cid,int uid);
	public boolean addFeedback(String feedback,int uid,int cid);
	public List<Course> getEnrolledCourse(int uid);
	public List<Video> getEnrolledCourseVideo(int uid,int cid);
	public boolean lockAccount(int uid);
	public boolean unlocakAccount(int uid);
	public List<User> getLockedAccount();
	public boolean generateCompeletionCerti(int uid,int cid);
	public boolean Enroll(int cid,int uid);
	public boolean nextVideo(int cid,int uid,int vid);
	public boolean completeVideo(int cid,int uid,int vid);
	
	// --------------------------------  User  ----------------------------------
	
		//for creating new user
		public abstract String createUser(User user);
		
		//for login page, checking credentials
		public abstract User forLogin(String userName, String email, String password);
		
		//for edit profile page and general function to get user details 
		public abstract Optional<User> getuserDetails(int id);
		
		//in change password to check old password present in db
		public abstract String getPassword(int id, String password);
		
		//to save new password in db
		public User changePassword( User user);
		
		//update user details like username, email
		public String editUserDetails(User user);
		
//		forget password remaining
		public abstract String getEmailSendTempPass(String email);
//		failed attempt remaining
		public abstract String genarateOTP();
		public abstract String generateTempPass();
		
		//while registration to check that same username exists in db
		public abstract String checkUsername(String username);
		
		//delete account(user)
		public abstract String deleteUser(int id); 
		
		// --------------------------------  Profile  ----------------------------------
		
		//create profile
		public abstract Profile createProfile(int userid, Profile profile);
		
		//get profile details
		public abstract Profile getProfileDetails(int userid);
		
		//update profile
		public abstract Profile editProfile(int userid, Profile profile);
		
	

}
