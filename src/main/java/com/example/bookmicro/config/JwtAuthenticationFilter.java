package com.example.bookmicro.config;

import java.io.IOException;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.bookmicro.helper.JwtUtil;
import com.example.bookmicro.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String requestTokenHeader = request.getHeader("Authorization");
		System.out.println(requestTokenHeader);
		String username = null;
		String jwtToken = null;
		
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			
			try {
				username = jwtUtil.extractUsername(jwtToken);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		if(null != username && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails
                    = customUserDetailsService.loadUserByUsername(username);

            if(jwtUtil.validateToken(jwtToken,userDetails)) {
            	
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }
		else {
			System.out.println(request.getRequestURI());
			System.out.println(request.getHeaderNames());
			System.out.println(requestTokenHeader);
			System.out.println("Token not valid" );
			Iterator<String> headers = request.getHeaderNames().asIterator();
			while(true) {
				if(headers.hasNext()) {
					String val = headers.next();
					System.out.println(val + request.getHeader(val));
				}
				else {
					break;
				}
			}
		}
		
		filterChain.doFilter(request, response);
		
		
		
	}

}
