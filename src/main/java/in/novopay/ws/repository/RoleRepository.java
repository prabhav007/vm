package in.novopay.ws.repository;

import org.springframework.data.repository.CrudRepository;

import in.novopay.ws.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	
	public Role findByCode(String roleCode);
}
