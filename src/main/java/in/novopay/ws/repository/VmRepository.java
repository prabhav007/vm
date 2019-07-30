package in.novopay.ws.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import in.novopay.ws.model.Vm;

public interface VmRepository extends PagingAndSortingRepository<Vm, Integer> {
	Page<Vm> findAllByUserId(Integer userId, Pageable pageable);
	
	List<Vm> findAllByUserIdOrderByRamDesc(Integer userId);
	List<Vm> findAllByOrderByRamDesc();
}
