package in.novopay.ws.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsDTO implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1866605928579817270L;
	private Integer id;
	private String username;
	private String password;
	private Collection<SimpleGrantedAuthority> authorities;

	public UserDetailsDTO() {
	}

	public UserDetailsDTO(Integer userId, String username, String password, String roleCode) {
		this.id = userId;
		this.username = username;
		this.password = password;
		authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(roleCode));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public Integer getUserId() {
		return id;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
