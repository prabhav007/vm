package in.novopay.ws.security;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.novopay.ws.dto.response.FailureResponse;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

//		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
		response.setContentType("application/json");
		FailureResponse failureResponse = new FailureResponse("AUTH001", HttpStatus.INTERNAL_SERVER_ERROR.value(), authException.getMessage(), authException.getMessage());
		OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, failureResponse);
        out.flush();
	}

}
