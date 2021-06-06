package com.example.demo.jpa;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);
	User findByPassword(String username);
		
}
