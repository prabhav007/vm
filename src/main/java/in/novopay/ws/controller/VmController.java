package in.novopay.ws.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import in.novopay.ws.dto.request.ProvisionVmRequest;
import in.novopay.ws.dto.response.GetVmListResponse;
import in.novopay.ws.dto.response.ProvisionVmResponse;
import in.novopay.ws.service.VmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/v1/")
@Api(description = "For operations pertaining to VMs")
public class VmController {

	@Autowired
	VmService vmService;

	@ApiOperation(value = "Provision VM for the logged in user")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "VM provisioned successfully"),
			@ApiResponse(code = 401, message = "Full authentication is required to access this resource") 
		})
	@PostMapping(value="/vms",produces="application/json")
	public ResponseEntity<ProvisionVmResponse> provisionVm(
			@ApiParam(value = "ProvisionVmRequest object which contains the details of the vm", required = true)
			@Valid @RequestBody ProvisionVmRequest provisionVmRequest, 
			UriComponentsBuilder builder) {
		ProvisionVmResponse provisionVmResponse = vmService.provisionVm(provisionVmRequest);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/vms/{id}").buildAndExpand(provisionVmResponse.getVmId()).toUri());
		return new ResponseEntity<>(provisionVmResponse, headers, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Top vms by memory")
	@ApiResponses(value = { 
			@ApiResponse(code = 401, message = "Full authentication is required to access this resource") 
		})
	@GetMapping("/top-vms")
	public ResponseEntity<GetVmListResponse> getTopVm(
			@ApiParam(value = "Page number for the result", required = false)
			@RequestParam("page") @Nullable Integer page, 
			@ApiParam(value = "Page size for the result", required = false)
			@RequestParam("size") @Nullable Integer size) {
		return new ResponseEntity<>(vmService.findTopVmsByMemory(page, size), HttpStatus.OK);
	}
}
