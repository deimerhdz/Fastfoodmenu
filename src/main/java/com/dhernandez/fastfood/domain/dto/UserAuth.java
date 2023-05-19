package com.dhernandez.fastfood.domain.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 17/05/2023
 */
public class UserAuth implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private String name;
	private Long id;
	
	public static UserAuth build(UserDto user) {
		return new UserAuth(user.getEmail(), user.getPassword(),user.getId(),user.getUsername());
	}
	
	public UserAuth(String email, String password,Long id,String name) {
		super();
		this.email = email;
		this.password = password;
		this.id =id;
		this.name =name;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
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

	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
