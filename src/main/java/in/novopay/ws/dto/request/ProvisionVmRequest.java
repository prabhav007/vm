package in.novopay.ws.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;

public class ProvisionVmRequest {

	@ApiModelProperty(notes = "os of the VM", allowableValues="Windows, Ubuntu, RHEL", required = true, example = "RHEL")
	@NotNull
	@Pattern(regexp = "Windows|Ubuntu|RHEL")
	private String os;

	@ApiModelProperty(notes = "ram of the VM (in GBs)", required = true, example = "16")
	@NotNull
	private Integer ram;

	@ApiModelProperty(notes = "disk size of the VM (in TBs)", required = true, example = "100")
	@NotNull
	private Integer disk;

	@ApiModelProperty(notes = "cpu cores of the VM", required = true, example = "6")
	@NotNull
	private Integer cores;

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
