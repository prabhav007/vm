package in.novopay.ws.dto.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class AuthenticateRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2910965007691394279L;
	
	@ApiModelProperty(notes = "Username for authentication, can be email/mobile", required=true, example="dave@google.com")
	private String username;
	
	@ApiModelProperty(notes = "Password for authentication", required=true, example="pass@123")
	private String password;

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
