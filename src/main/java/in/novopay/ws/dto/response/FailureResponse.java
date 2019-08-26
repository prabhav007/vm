package in.novopay.ws.dto.response;

import java.util.List;

public class FailureResponse extends GenericResponse {
	private List<FieldValidationError> subErrors;

	public FailureResponse() {
		super();
		this.setResult(Result.fail);
	}

	public FailureResponse(String code, String status, String message, String exceptionMessage) {
		super(code, status, message, exceptionMessage);
		this.setResult(Result.fail);
	}

	public FailureResponse(String code, Integer status, String message, String exceptionMessage) {
		super(code, status, message, exceptionMessage);
		this.setResult(Result.fail);
	}
	
	public FailureResponse(String code, String status, String message, String exceptionMessage, List<FieldValidationError> errors) {
		super(code, status, message, exceptionMessage);
		this.setResult(Result.fail);
		this.setSubErrors(errors);
	}
	
	public FailureResponse(String code, Integer status, String message, String exceptionMessage, List<FieldValidationError> errors) {
		super(code, status, message, exceptionMessage);
		this.setResult(Result.fail);
		this.setSubErrors(errors);
	}

	public List<FieldValidationError> getSubErrors() {
		return subErrors;
	}

	public void setSubErrors(List<FieldValidationError> subErrors) {
		this.subErrors = subErrors;
	}	

}
