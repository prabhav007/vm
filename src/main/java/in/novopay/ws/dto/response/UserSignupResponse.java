package in.novopay.ws.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class UserSignupResponse extends GenericResponse {
	
	@ApiModelProperty(notes = "Userid of the user", required=true, example="1")
	private Integer userId;
	
	public UserSignupResponse() {
		this.setResult(Result.success);
		this.setMessage("User signup successful");
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
