package in.novopay.ws.dto.response;

import java.util.List;

public class FailureReponse extends GenericResponse {
	private List<FieldValidationError> errors;

	public FailureReponse() {
		super();
		this.setResult(Result.fail);
	}

	public FailureReponse(List<FieldValidationError> errors) {
		super();
		this.setResult(Result.fail);
		this.setErrors(errors);
	}

	public FailureReponse(String message) {
		super(message);
		this.setResult(Result.fail);
	}

	public FailureReponse(String message, List<FieldValidationError> errors) {
		super(message);
		this.setResult(Result.fail);
		this.setErrors(errors);
	}

	public List<FieldValidationError> getErrors() {
		return errors;
	}

	public void setErrors(List<FieldValidationError> errors) {
		this.errors = errors;
	}
	
	

}
