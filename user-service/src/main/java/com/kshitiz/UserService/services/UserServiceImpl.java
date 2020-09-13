package com.kshitiz.UserService.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kshitiz.UserService.exceptions.UserNotFoundException;
import com.kshitiz.UserService.model.User;
import com.kshitiz.UserService.repo.UserRepository;
import com.kshitiz.UserService.shared.Header;
import com.kshitiz.UserService.shared.UserAuthenticate;
import com.kshitiz.UserService.shared.UserRequestModel;
import com.kshitiz.UserService.shared.UserResponseModel;
import com.kshitiz.UserService.shared.UserUpdateModel;

import org.modelmapper.TypeToken;
import java.lang.reflect.Type;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	private ModelMapper mapper;

	public UserServiceImpl(UserRepository repo, ModelMapper modelMapper) {
		this.userRepository = repo;
		this.mapper = modelMapper;
	}

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	@Transactional
	public String createNewUser(UserRequestModel userRequestModel) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		logger.info("userRequestModel -> {} ", userRequestModel);
		User user = userRepository.findUserByUserIdIgnoreCase(userRequestModel.getUserId());
		if(user!=null){
			return "failed";
		}else{
			if(userRequestModel.getRole() ==null){userRequestModel.setRole("user");}		
			userRepository.save(mapper.map(userRequestModel, User.class));
			return "successful";
		}
	}

	@Override
	@Transactional
	public List<UserResponseModel> getAllUsers() {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Type listType = new TypeToken<List<UserResponseModel>>() {
		}.getType();
		List<UserResponseModel> list = mapper.map(userRepository.findAll(), listType);
		return list;
	}

	

	@Override
	@Transactional
	public UserResponseModel getUserByUserId(String id) throws UserNotFoundException {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		User user = userRepository.findUserByUserIdIgnoreCase(id);
		logger.info("User of  id {} is {}", id, user);

		if (user == null) {
			throw new UserNotFoundException("user not found with id " + id);
		}

		return mapper.map(user, UserResponseModel.class);
	}

	@Override
	public Header authenticateUser(UserAuthenticate userRequest)  {
		
		Header header = new Header();	
		logger.info("User Authenticate -> {}", userRequest);
		User user = userRepository.findByUserIdIgnoreCaseAndPassword(userRequest.getUserId(), userRequest.getPassword());	
		if(user == null){
			header.setStatus("failed");
			return header;
		}else{
			header.setUserId(user.getUserId());
			header.setRole(user.getRole());
			header.setStatus("successful");
			header.setToken("");
			return header;			
		}	
	}

	@Override
	public String removeUser(String userId)  throws UserNotFoundException{
		User user = userRepository.findUserByUserIdIgnoreCase(userId);

		if(user ==null){throw new UserNotFoundException("No user with userId "+ userId);}
		userRepository.delete(user);
		return "Removed user with userId "+ userId;
	}

	@Override
	public String removeAll() {
		userRepository.deleteAll();
		return "Removed all users";
	}

	@Override
	@Transactional
	public String updateUser(UserUpdateModel userUpdateModel, String userId) {
		User user = userRepository.findByUserIdIgnoreCaseAndPassword(userId, userUpdateModel.getOldPassword());
		

		if(user == null){
			return "failed";	
		}else{
			if(userUpdateModel.getNewPassword().length()>0){
				user.setPassword(userUpdateModel.getNewPassword());
				logger.info("new password accepted -> {}", userUpdateModel.getNewPassword());
			}
			user.setEmail(userUpdateModel.getEmail().length()>0? userUpdateModel.getEmail():user.getEmail());
			user.setFirstName(userUpdateModel.getFirstName().length()>0? userUpdateModel.getFirstName():user.getFirstName());
			user.setLastName(userUpdateModel.getLastName().length()>0? userUpdateModel.getLastName():user.getLastName());
			user.setUserId(userUpdateModel.getUserId().length()>0? userUpdateModel.getUserId():user.getUserId());
			logger.info("updated user -> {}",user);
			return "successful";
		}
		
	}

	
}
