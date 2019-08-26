package in.novopay.ws.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class GenericResponse {
	
	public enum Result {
		success, fail
	}
	

	private String code;
	private String status;
	
	@ApiModelProperty(notes = "result of the api - can be success/fail", required=true, example="success")
	private Result result;
	
	@ApiModelProperty(notes = "response message", example="use case specific message")
	private String message;
	private String exception;
	
	public GenericResponse() {

	}
	
	public GenericResponse(String code, String status, String message, String exceptionMessage) {
		this.code= code;
		this.message = message;
		this.status = status;
		this.exception = exceptionMessage;
	}
	
	public GenericResponse(String code, Integer status, String message, String exceptionMessage) {
		this.code= code;
		this.message = message;
		this.status = String.valueOf(status);
		this.exception = exceptionMessage;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExceptionMessage() {
		return exception;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exception = exceptionMessage;
	}

}
