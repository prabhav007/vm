package in.novopay.ws.exception;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;

public class CustomResponseStatusException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8255213098368348202L;
	
	private final HttpStatus status;
	private final String code;
	

	public CustomResponseStatusException(HttpStatus status, String code) {
		this.code = code;
		this.status = status;
	}

	@Override
	public String getMessage() {
		String msg = this.status.name();
		return NestedExceptionUtils.buildMessage(msg, getCause());
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getCode() {
		return code;
	}
}
