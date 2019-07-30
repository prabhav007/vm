package in.novopay.ws.dto.response;

public class ProvisionVmResponse extends GenericResponse {
	private Integer vmId;
	
	public ProvisionVmResponse() {
		this.setResult(Result.success);
		this.setMessage("VM provisioned successfully");
	}

	public Integer getVmId() {
		return vmId;
	}

	public void setVmId(Integer vmId) {
		this.vmId = vmId;
	}

}
