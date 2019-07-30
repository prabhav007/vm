package in.novopay.ws.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import in.novopay.ws.dto.request.UserSignupRequest;
import in.novopay.ws.dto.response.DeleteUserResponse;
import in.novopay.ws.dto.response.GetVmListResponse;
import in.novopay.ws.dto.response.UserSignupResponse;
import in.novopay.ws.service.UserService;
import in.novopay.ws.service.VmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "api/v1/users")
@Api(description = "For operations pertaining to the user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	VmService vmService;

	@ApiOperation(value = "Signup for a new user")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "User signup successful") 
		})
	@PostMapping(value = "", produces = "application/json")
	public ResponseEntity<UserSignupResponse> userSignup(
			@ApiParam(value = "UserSignupRequest object which contains the details of the user", required = true) 
			@Valid @RequestBody UserSignupRequest userSignupRequest,
			UriComponentsBuilder builder) {
		UserSignupResponse response = userService.userSignup(userSignupRequest);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/users/{id}").buildAndExpand(response.getUserId()).toUri());
		return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Delete a user")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "User deleted successfully"), 
			@ApiResponse(code = 401, message = "Full authentication is required to access this resource") 
		})
	@DeleteMapping(value = "/{id}", produces="application/json")
	public ResponseEntity<DeleteUserResponse> deleteUser(
			@ApiParam(value = "Userid of the user to be deleted", required = true) 
			@PathVariable("id") Integer userId) {
		DeleteUserResponse deleteUserResponse = userService.deleteUser(userId);
		return new ResponseEntity<>(deleteUserResponse, HttpStatus.OK);
	}

	@ApiOperation(value = "Get the VMs for a particular user")
	@ApiResponses(value = { 
			@ApiResponse(code = 401, message = "Full authentication is required to access this resource") 
		})
	@GetMapping(value = "/{id}/vms", produces="application/json")
	public ResponseEntity<GetVmListResponse> getUserVms(
			@ApiParam(value = "Userid of the user to fetch VMs of", required = true)
			@PathVariable("id") Integer userId,
			@ApiParam(value = "Page number for the result", required = false)
			@RequestParam("page") @Nullable Integer page, 
			@ApiParam(value = "Page size for the result", required = false)
			@RequestParam("size") @Nullable Integer size) {
		return new ResponseEntity<>(vmService.findByUserId(userId, page, size), HttpStatus.OK);
	}
}
