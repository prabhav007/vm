package in.novopay.ws.repository;

import org.springframework.data.repository.CrudRepository;

import in.novopay.ws.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	public User findOneByEmail(String email);
	public User findOneByMobile(String mobile);
	public User findOneByEmailOrMobile(String email, String mobile);
}
