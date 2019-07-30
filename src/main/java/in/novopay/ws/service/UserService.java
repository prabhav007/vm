package in.novopay.ws.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.novopay.ws.dto.UserDetailsDTO;
import in.novopay.ws.dto.request.UserSignupRequest;
import in.novopay.ws.dto.response.DeleteUserResponse;
import in.novopay.ws.dto.response.UserSignupResponse;
import in.novopay.ws.exception.CustomException;
import in.novopay.ws.model.Role;
import in.novopay.ws.model.User;
import in.novopay.ws.repository.RoleRepository;
import in.novopay.ws.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserSignupResponse userSignup(UserSignupRequest userSignupRequest) {
		
		//Dedup for email
		User user = userRepository.findOneByEmail(userSignupRequest.getEmail());
		if(null!=user) {
			throw new CustomException(HttpStatus.BAD_REQUEST, "email.already.exists");
		}
		
		//Dedup for mobile
		user = userRepository.findOneByMobile(userSignupRequest.getMobile());
		if(null!=user) {
			throw new CustomException(HttpStatus.BAD_REQUEST, "mobile.already.exists");
		}
		
		//Validate role from the request
		Role role = roleRepository.findByCode(userSignupRequest.getRole());
		if (null == role) {
			throw new CustomException(HttpStatus.NOT_FOUND, "role.not.found");
		}
		
		user = new User();
		user.setName(userSignupRequest.getName());
		user.setEmail(userSignupRequest.getEmail());
		user.setMobile(userSignupRequest.getMobile());
		user.setPassword(passwordEncoder.encode(userSignupRequest.getPassword()));
		user.setRoleId(role.getId());
		user = userRepository.save(user);

		UserSignupResponse response = new UserSignupResponse();
		response.setUserId(user.getId());
		return response;
	}

	public void validateUserId(Integer userId) {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			throw new CustomException(HttpStatus.NOT_FOUND, "userid.not.found");
		}
	}

	public DeleteUserResponse deleteUser(Integer userId) {
		validateUserId(userId);
		userRepository.deleteById(userId);
		DeleteUserResponse deleteUserResponse = new DeleteUserResponse();
		return deleteUserResponse;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findOneByEmailOrMobile(username, username);
		if (null == user) {
			throw new UsernameNotFoundException("Invalid username");
		}
		Optional<Role> role = roleRepository.findById(user.getRoleId());
		return new UserDetailsDTO(user.getId(), username, user.getPassword(), role.get().getCode());
	}
}
