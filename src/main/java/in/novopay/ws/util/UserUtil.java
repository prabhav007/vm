package in.novopay.ws.util;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import in.novopay.ws.dto.UserDetailsDTO;

public class UserUtil {

	public static boolean currentUserHasRole(String roleCode) {
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for(GrantedAuthority grantedAuthority:authorities) {
			if(roleCode.equals(grantedAuthority.getAuthority())) {
				return true;
			}
		}
		return false;
	}
	
	public static Integer getCurrentUserId() {
		UserDetailsDTO userDetails = (UserDetailsDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails.getUserId();
	}
}
