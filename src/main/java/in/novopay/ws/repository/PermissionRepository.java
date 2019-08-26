package in.novopay.ws.repository;

import org.springframework.data.repository.CrudRepository;

import in.novopay.ws.model.Permission;

public interface PermissionRepository extends CrudRepository<Permission, Integer> {
	
}
