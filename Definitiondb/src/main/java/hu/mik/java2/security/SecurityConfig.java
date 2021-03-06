package hu.mik.java2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
	
	private static final String LOGIN_PAGE = "/login";
	private static final String[] FREE_PAGES_ANT = { "/VAADIN/**", "/vaadinServlet/**", LOGIN_PAGE };
	private static final String DEFAULT_PAGE_URL = "/definicioUI";
	private static final String SECURITY_URL = "/j_spring_security_check";
	private static final String ROLE_USER="USER";
	private static final String ROLE_ADMIN="ADMIN";
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers(FREE_PAGES_ANT).permitAll()
		.antMatchers("/definicioUI#!definicion").hasAnyRole(ROLE_USER)
		.antMatchers("/definicioUI#**").hasAnyRole(ROLE_ADMIN)
		.anyRequest().hasAnyRole(ROLE_USER)
		.and().formLogin().loginPage(LOGIN_PAGE)
		.loginProcessingUrl(SECURITY_URL)
		.defaultSuccessUrl(DEFAULT_PAGE_URL, true)
		.permitAll();
		http.headers().frameOptions().sameOrigin();
		http.csrf().disable();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		  auth.inMemoryAuthentication().withUser("123").password("12345678").roles(ROLE_USER);
		  auth.inMemoryAuthentication().withUser("admin").password("admin").roles(ROLE_USER, ROLE_ADMIN);
		  
	}
	
}   
