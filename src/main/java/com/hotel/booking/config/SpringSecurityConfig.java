package com.hotel.booking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	// roles admin allow to access /admin/**
	// roles user allow to access /user/**
	// custom 403 access denied handler
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/login", "/home", "/about", "/css/**", "/webjars/**", "/403").permitAll()
				.antMatchers("/admin/**").hasAnyRole("ADMIN").antMatchers("/user/**").hasAnyRole("USER").anyRequest()
				.authenticated().and()
				// .requiresChannel().anyRequest().requiresSecure()
				.formLogin().loginPage("/login").permitAll().and().logout().permitAll().and().exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler)
				.and()
                .csrf().disable()
                .requiresChannel()		//config request to use the mapping to a required channel
					.anyRequest().requiresSecure();
		
		http
        .portMapper()
			.http(80).mapsTo(443);

		// http.requiresChannel().anyRequest().requiresSecure();
	}

	// create two users, admin and user
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER").and().withUser("admin")
				.password("{noop}password").roles("ADMIN");
	}

}
