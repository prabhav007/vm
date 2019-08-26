package in.novopay.ws.dto.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class AuthenticateResponse extends GenericResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6756979891554593009L;
	@ApiModelProperty(notes = "jwt token", required=true, example="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5NjY")
	private final String jwttoken;

	public AuthenticateResponse(String jwttoken) {
		super("","","Login successful", "");
		this.setResult(Result.success);
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}
