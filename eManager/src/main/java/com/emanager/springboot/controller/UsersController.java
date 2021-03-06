package com.emanager.springboot.controller;
//create rest api

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emanager.springboot.exception.ResourceNotFoundException;
import com.emanager.springboot.model.*;
import com.emanager.springboot.repository.UsersRepository;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class UsersController {
	
	@Autowired
	private UsersRepository usersRepository;
	
	//get all employess
	@GetMapping("/users")
	public List<Users> getAllUsers(){
		return usersRepository.findAll();
		
	}
	
	// Create users rest api
	@PostMapping("/users")
	public Users createUser(@RequestBody Users user) {
		return usersRepository.save(user);
	}
	
	// get employee by id rest api
		@GetMapping("/users/{id}")
		public ResponseEntity<Users> getEmployeeById(@PathVariable Long id) {
			Users users = usersRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("User does not exist with id :" + id));
			return ResponseEntity.ok(users);
		}
		
		// update employee rest api
		
		
		
		@PutMapping("/users/{id}")
		public ResponseEntity<Users> updateUsers(@PathVariable Long id, @RequestBody Users usersDetails){
			Users user = usersRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("User does not exist with id :" + id));
			
			user.setFirstName(usersDetails.getFirstName());
			user.setLastName(usersDetails.getLastName());
			user.setEmailId(usersDetails.getEmailId());
			user.setBirthDate(usersDetails.getBirthDate());
			user.setAddress1(usersDetails.getAddress1());
			user.setAddress2(usersDetails.getAddress2());
			user.setZip(usersDetails.getZip());
			user.setCountry(usersDetails.getCountry());
			user.setCity(usersDetails.getCity());
			user.setState(usersDetails.getState());
			
			Users updatedUsers = usersRepository.save(user);
			return ResponseEntity.ok(updatedUsers);
			
		}
		@DeleteMapping("/users/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
			Users user = usersRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("User does not exist with id :" + id));
			
			usersRepository.delete(user);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
		
}
