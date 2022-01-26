package com.hasandayan.todoapp.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hasandayan.todoapp.service.AppUserService;
import com.hasandayan.todoapp.util.JwtTokenUtil;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AppUserService appUserService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		String tokenHeader = request.getHeader("Authorization");

		String jwtToken = "";
		String username = "";
		
		if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
			jwtToken = tokenHeader.substring(7);
			
			username = jwtTokenUtil.retrieveUsernameFromToken(jwtToken);
		}

		if (!username.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = appUserService.loadUserByUsername(username);

			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}

		filterChain.doFilter(request, response);

	}

}
