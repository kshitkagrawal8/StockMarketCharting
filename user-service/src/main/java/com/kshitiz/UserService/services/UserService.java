package com.kshitiz.UserService.services;


import java.util.List;

import com.kshitiz.UserService.exceptions.UserNotFoundException;
import com.kshitiz.UserService.shared.Header;
import com.kshitiz.UserService.shared.UserAuthenticate;
import com.kshitiz.UserService.shared.UserRequestModel;
import com.kshitiz.UserService.shared.UserResponseModel;
import com.kshitiz.UserService.shared.UserUpdateModel;

public interface UserService {

	public String createNewUser(UserRequestModel userRequestModel);

	public List<UserResponseModel> getAllUsers();


	public UserResponseModel getUserByUserId(String id) throws UserNotFoundException;

	public Header authenticateUser(UserAuthenticate user) ;

	public String removeUser(String userId) throws UserNotFoundException;

	public String removeAll();

	public String updateUser(UserUpdateModel userUpdateModel, String userId);
}
