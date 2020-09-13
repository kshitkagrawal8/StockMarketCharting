package com.kshitiz.UserService.Controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kshitiz.UserService.exceptions.UserNotFoundException;
import com.kshitiz.UserService.services.UserService;
import com.kshitiz.UserService.shared.Header;
import com.kshitiz.UserService.shared.UserAuthenticate;
import com.kshitiz.UserService.shared.UserRequestModel;
import com.kshitiz.UserService.shared.UserResponseModel;
import com.kshitiz.UserService.shared.UserUpdateModel;

@RestController
@RequestMapping("/users")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public Map<String, String> addUser(@RequestBody UserRequestModel userRequestModel) {
		String status = userService.createNewUser(userRequestModel);
		Map<String, String> map = new HashMap<>();
		map.put("signup", status);		
		return map;
	}
	
	@GetMapping("/all")
	public ResponseEntity<Object> getAllUsers() throws UserNotFoundException{
		List<UserResponseModel> allUsers = userService.getAllUsers();
		if(allUsers.size() ==0){ throw new UserNotFoundException("No user in Database");}
		return ResponseEntity.status(HttpStatus.OK).body( allUsers );
	}
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserResponseModel> getUserByUserId(@PathVariable("userId") String id) throws UserNotFoundException{
		
		UserResponseModel user = userService.getUserByUserId(id);
		
		if(user == null) { throw new UserNotFoundException("User not Found with userId " + id );}
		return ResponseEntity.status(HttpStatus.OK).body( user);
	}

	@PostMapping("/login")
	public ResponseEntity<Header> authenticateUser(@RequestBody UserAuthenticate user){

		return ResponseEntity.status(HttpStatus.OK).body(userService.authenticateUser(user));
	}

	@GetMapping("/remove/{userId}")
	public ResponseEntity<String> removeUser(@PathVariable String userId) throws UserNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(userService.removeUser(userId));
	}
	@GetMapping("/remove/all")
	public ResponseEntity<String> removeAll()  {
		return ResponseEntity.status(HttpStatus.OK).body(userService.removeAll());
	}

	@PostMapping("/update/{userId}")
	public ResponseEntity<Map<String, String>> updateUser(@RequestBody UserUpdateModel userUpdateModel, @PathVariable String userId){
		Map<String, String> map = new HashMap<>();
		map.put("status",userService.updateUser(userUpdateModel, userId));
		return ResponseEntity.status(HttpStatus.OK).body(map);

	}
}
