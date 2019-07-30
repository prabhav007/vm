package in.novopay.ws.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class GenericResponse {
	public enum Result {
		success, fail
	}
	@ApiModelProperty(notes = "result of the api - can be success/fail", required=true, example="success")
	private Result result;
	
	@ApiModelProperty(notes = "response message", example="use case specific message")
	private String message;
	
	public GenericResponse() {

	}
	
	public GenericResponse(String message) {
		this.message = message;
	}

	public String getResult() {
		return result.name();
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public void setResult(String result) {
		this.result = Result.valueOf(result);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
