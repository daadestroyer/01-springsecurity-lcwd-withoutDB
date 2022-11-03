package com.daadestroyer.springsecurityapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

// 		 WAY - 1
//		 http
//		 		.cors().disable()
//		 		.csrf().disable()
//		 		.authorizeRequests()
//		 		.antMatchers("/admin/**").hasRole("ADMIN")
//		 		.antMatchers("/user/**").hasRole("USER")
//		 		.antMatchers("/public/**").permitAll()
//		 		.anyRequest()
//		 		.authenticated()
//		 		.and()
//		 		.formLogin();

// 		 WAY - 2
		http
			.cors().disable()
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/user/**").hasAnyRole("USER")
			.antMatchers("/signin").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
			.loginPage("/signin")
			.loginProcessingUrl("/doLogin")
			.defaultSuccessUrl("/dashboard");
		
		http
			.logout().logoutUrl("/doLogout");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("shubham").password(this.bCryptPasswordEncoder().encode("shubham123"))
				.roles("ADMIN");
		auth.inMemoryAuthentication().withUser("pankaj").password(this.bCryptPasswordEncoder().encode("pankaj123"))
				.roles("USER");
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

}
