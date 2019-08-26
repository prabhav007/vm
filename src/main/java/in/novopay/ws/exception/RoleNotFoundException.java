package in.novopay.ws.exception;

import org.springframework.http.HttpStatus;

public class RoleNotFoundException extends CustomResponseStatusException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1364432963399853779L;

	public RoleNotFoundException() {
		super(HttpStatus.BAD_REQUEST, "BU003");
	}

}
