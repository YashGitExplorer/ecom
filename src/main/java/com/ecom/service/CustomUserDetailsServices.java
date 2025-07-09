package com.ecom.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserDetailsServices {
	 public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

}
