package org.basic.spring.security.rest.config;

import org.basic.spring.security.rest.enums.Authoritiy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/**
 * This application is secured at both the URL level for some parts, and the
 * method level for other parts. The URL security is shown inside this code,
 * while method-level annotations are enabled at by
 * {@link EnableGlobalMethodSecurity}.
 *
 * @author Bijoy Paul
 */
@EnableWebSecurity
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BasicAuthenticationEntryPoint entryPoint;

	@Autowired
	private AccessDeniedHandler handler;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		 http.csrf().disable()
         .authorizeRequests()
         .antMatchers("/user/count").permitAll()
         .antMatchers("/user/profile").hasRole(Authoritiy.USER.toString())
         .antMatchers("/user/**").hasRole(Authoritiy.ADMIN.toString())
         .and()
         .httpBasic().authenticationEntryPoint(entryPoint)
         .and()
         .exceptionHandling().accessDeniedHandler(handler);
	}
}
