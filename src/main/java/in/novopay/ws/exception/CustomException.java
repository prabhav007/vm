package in.novopay.ws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.server.ResponseStatusException;

public class CustomException extends ResponseStatusException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4119956627789285179L;
	
	private final String messageCode;
	
	public CustomException(HttpStatus status, String messageCode) {
		super(status);
		this.messageCode=messageCode;
	}
	
	public CustomException(HttpStatus status, String messageCode, @Nullable Throwable cause) {
		super(status, null, cause);
		this.messageCode=messageCode;
	}

	public String getMessageCode() {
		return messageCode;
	}
	
}
