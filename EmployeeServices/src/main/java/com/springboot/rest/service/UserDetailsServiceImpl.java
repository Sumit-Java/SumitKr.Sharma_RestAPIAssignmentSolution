package com.springboot.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.springboot.rest.entity.User;
import com.springboot.rest.repository.UserRepository;
import com.springboot.rest.security.MyUserDetails;

public class UserDetailsServiceImpl implements UserDetailsService {

	//Auto injecting Bean for User Repository
	@Autowired
	private UserRepository userRepository;
	
	
	//Function to fetch the User Details based upon login details received
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		User user = userRepository.getUserByUserName(userName);
		
		if(user==null)
		{
			throw new UsernameNotFoundException("User Not Found");
		}
		
		return new MyUserDetails(user);
	}

}
