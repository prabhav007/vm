package in.novopay.ws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import in.novopay.ws.dto.VmDTO;
import in.novopay.ws.dto.request.ProvisionVmRequest;
import in.novopay.ws.dto.response.GetVmListResponse;
import in.novopay.ws.dto.response.ProvisionVmResponse;
import in.novopay.ws.model.Vm;
import in.novopay.ws.repository.VmRepository;
import in.novopay.ws.util.UserUtil;

@Service
public class VmService {

	@Autowired
	VmRepository vmRepository;

	@Autowired
	private UserService userService;

	public ProvisionVmResponse provisionVm(ProvisionVmRequest provisionVmRequest) {
		Vm vm = new Vm();
		vm.setUserId(UserUtil.getCurrentUserId());
		vm.setOs(provisionVmRequest.getOs());
		vm.setRam(provisionVmRequest.getRam());
		vm.setDisk(provisionVmRequest.getDisk());
		vm.setCores(provisionVmRequest.getCores());
		vm = vmRepository.save(vm);
		
		ProvisionVmResponse provisionVmResponse = new ProvisionVmResponse();
		provisionVmResponse.setVmId(vm.getId());
		
		return provisionVmResponse;
	}

	public GetVmListResponse findByUserId(Integer userId, Integer page, Integer size) {
		userService.validateUserId(userId);
		if(null==page) {
			page = 0;
		}
		if(null==size || size>100) {
			size=100;
		}
		Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.DESC,"ram"));
		Page<Vm> vmList = vmRepository.findAllByUserId(userId, pageable);
		GetVmListResponse response = new GetVmListResponse();
		response.setVmList(populateDTOList(vmList));
		return response;
	}

	public GetVmListResponse findTopVmsByMemory(Integer page, Integer size) {
		if(null==page) {
			page = 0;
		}
		if(null==size || size>100) {
			size=100;
		}
		Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.DESC,"ram"));
		Page<Vm> vmList;
		if (UserUtil.currentUserHasRole("MASTER")) {
			vmList = vmRepository.findAll(pageable);
		} else {
			vmList = vmRepository.findAllByUserId(UserUtil.getCurrentUserId(), pageable);
		}
		GetVmListResponse response = new GetVmListResponse();
		response.setVmList(populateDTOList(vmList));
		return response;
	}

	private List<VmDTO> populateDTOList(Page<Vm> vmList) {
		List<VmDTO> vmDTOList = new ArrayList<>();
		VmDTO vmDTO;
		for (Vm vm : vmList) {
			vmDTO = new VmDTO(vm);
			vmDTOList.add(vmDTO);
		}
		return vmDTOList;
	}
}
