package in.novopay.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.novopay.ws.dto.request.AuthenticateRequest;
import in.novopay.ws.dto.response.AuthenticateResponse;
import in.novopay.ws.exception.CustomException;
import in.novopay.ws.service.UserService;
import in.novopay.ws.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "api/v1/")
@Api(description="For authenticating users")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Authenticate a user")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Login Successful"),
		    @ApiResponse(code = 401, message = "Invalid username/password"),
		    @ApiResponse(code = 403, message = "User is disabled")
		})
	@PostMapping(value = "/authenticate", produces = "application/json")
	public ResponseEntity<AuthenticateResponse> createAuthenticationToken(
			@ApiParam(value = "AuthenticateRequest object which contains the credentials", required = true) 
			@RequestBody AuthenticateRequest authenticateRequest) throws Exception {
		authenticate(authenticateRequest.getUsername(), authenticateRequest.getPassword());
		final UserDetails userDetails = userService.loadUserByUsername(authenticateRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticateResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new CustomException(HttpStatus.FORBIDDEN, "Exception.disabled.user");
		} catch (BadCredentialsException e) {
			throw new CustomException(HttpStatus.UNAUTHORIZED, "Exception.bad.credentials");
		}
	}

}
