package in.novopay.ws.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class UserSignupRequest {
	@ApiModelProperty(notes = "Name of the user", required=true, example="dave")
	@NotBlank
	@Size(min=3,max=128)
	@Pattern(regexp="[A-Za-z ]*")
	private String name;
	
	@ApiModelProperty(notes = "Email of the user", required=true, example="dave@google.com")
	@NotBlank
	@Size(min=6,max=128)
	@Pattern(regexp="^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
	private String email;
	
	@ApiModelProperty(notes = "Mobile number of the user", required=true, example="9895955555")
	@NotBlank
	@Size(min=10,max=10)	
	@Pattern(regexp="[1-9][0-9]{9,9}")
	private String mobile;
	
	@ApiModelProperty(notes = "Password of the user", required=true, example="pass@123")
	@NotBlank
	@Size(min=7,max=32)
	private String password;

	@ApiModelProperty(notes = "Role of the user", allowableValues="MASTER,NON-MASTER", required=true, example="MASTER")
	@NotBlank
	@Size(min=1,max=32)
	private String role;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
