package com.ecom.serviceImpl;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecom.entity.Users;
import com.ecom.repository.UsersRepository;
import com.ecom.service.CustomUserDetailsServices;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServicesImpl implements CustomUserDetailsServices{

	private UsersRepository usersRepository ;
	
	 @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        Users user = usersRepository.findByEmail(email)
	            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

	        return new org.springframework.security.core.userdetails.User(
	            user.getEmail(),
	            user.getPassword(),
	            List.of(new SimpleGrantedAuthority("ROLE_USER"))
	        );
	    }
}
