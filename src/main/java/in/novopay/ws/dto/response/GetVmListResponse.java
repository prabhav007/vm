package in.novopay.ws.dto.response;

import java.util.List;

import in.novopay.ws.dto.VmDTO;

public class GetVmListResponse extends GenericResponse {
	private List<VmDTO> vmList;
	
	public GetVmListResponse() {
		this.setResult(Result.success);
	}

	public List<VmDTO> getVmList() {
		return vmList;
	}

	public void setVmList(List<VmDTO> vmList) {
		this.vmList = vmList;
	}

}
