package com.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
	
	Optional<Users> findByEmail(String email);

	@Query("select coalesce(Max(u.userId),0) from Users u ")
	Long findMaxId();

}
