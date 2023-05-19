package com.dhernandez.fastfood.web.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dhernandez.fastfood.domain.services.UserDetailServiceImpl;
import com.dhernandez.fastfood.domain.services.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com> Creado en 17/05/2023
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	UserDetailServiceImpl UserDetailServiceImpl;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filter)
			throws ServletException, IOException {
		try {
			String token = request.getHeader("Authorization");
			if (token != null && token.contains("Bearer")) {
				token = token.replace("Bearer", "");
				
				if (jwtProvider.validateToken(token)) {

					String email = jwtProvider.extractUsername(token);
					UserDetails userDatail = UserDetailServiceImpl.loadUserByUsername(email);
				
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDatail, null,
							userDatail.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}

			
		} catch (Exception e) {

		}
		filter.doFilter(request, response);

	}

}
