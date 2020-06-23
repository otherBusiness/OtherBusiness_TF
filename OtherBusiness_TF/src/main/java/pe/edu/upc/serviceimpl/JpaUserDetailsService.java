package pe.edu.upc.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Role;
import pe.edu.upc.entity.Users;
import pe.edu.upc.repository.IUserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {
	
	@Autowired
	private IUserRepository iuserRepository;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String usernameUser) throws UsernameNotFoundException {
		Users user = iuserRepository.findByUsernameUser(usernameUser);

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getNameRol()));
		}

		return new User(user.getUsernameUser(), user.getPasswordUser(), user.getEnabledUser(), true, true, true, authorities);
	}
	}
	
	
	
	
	
	
	
/*
	@Autowired
	private IUserRepository userRepository;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findByUsername(username);

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getNameRol()));
		}

		return new User(user.getUsernameUser(), user.getPasswordUser(), user.getEnabledUser(), true, true, true, authorities);
	}
*/

