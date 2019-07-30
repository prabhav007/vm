package in.novopay.ws.dto;

import in.novopay.ws.model.Vm;
import io.swagger.annotations.ApiModelProperty;

public class VmDTO {
	@ApiModelProperty(notes = "id of the VM", required = true, example = "1")
	private Integer id;

	@ApiModelProperty(notes = "user id of the user to whom VM is provisioned", required = true, example = "1")
	private Integer userId;
	
	@ApiModelProperty(notes = "os of the VM", allowableValues="Windows, Ubuntu, RHEL", required = true, example = "RHEL")
	private String os;
	
	@ApiModelProperty(notes = "ram of the VM (in GBs)", required = true, example = "16")
	private Integer ram;
	
	@ApiModelProperty(notes = "disk size of the VM (in TBs)", required = true, example = "100")
	private Integer disk;
	
	@ApiModelProperty(notes = "cpu cores of the VM", required = true, example = "6")
	private Integer cores;

	public VmDTO() {
	}

	public VmDTO(Vm vm) {
		this.id = vm.getId();
		this.userId = vm.getUserId();
		this.os = vm.getOs();
		this.ram = vm.getRam();
		this.disk = vm.getDisk();
		this.cores = vm.getCores();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Integer getRam() {
		return ram;
	}

	public void setRam(Integer ram) {
		this.ram = ram;
	}

	public Integer getDisk() {
		return disk;
	}

	public void setDisk(Integer disk) {
		this.disk = disk;
	}

	public Integer getCores() {
		return cores;
	}

	public void setCores(Integer cores) {
		this.cores = cores;
	}

}
