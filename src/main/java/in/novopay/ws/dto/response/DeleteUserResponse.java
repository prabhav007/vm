package in.novopay.ws.dto.response;

public class DeleteUserResponse extends GenericResponse {

	public DeleteUserResponse() {
		this.setResult(Result.success);
		this.setMessage("User deleted successfully");
	}
}
