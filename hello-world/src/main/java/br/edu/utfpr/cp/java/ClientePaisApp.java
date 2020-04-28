package br.edu.utfpr.cp.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableWebSecurity
public class ClientePaisApp extends WebSecurityConfigurerAdapter {

	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	// 	auth.inMemoryAuthentication()
	// 		.withUser("john").password(encoder().encode("doe")).roles("user").and()
	// 		.withUser("anna").password(encoder().encode("doedoe")).roles("user", "admin");
	// }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/admin").hasAuthority("admin")
			.antMatchers("/user").hasAuthority("user")
			.antMatchers("/private").fullyAuthenticated()
			.antMatchers("/public").permitAll()
			.antMatchers("/login*").permitAll()
			.antMatchers("/logout*").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login.html")
			.defaultSuccessUrl("/paises", false)
			.and()
			.logout();
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(ClientePaisApp.class, args);
	}

	// @EventListener(ApplicationReadyEvent.class)
	// public void init() {
	// 	System.out.println(new BCryptPasswordEncoder().encode("doe"));
	// 	System.out.println(new BCryptPasswordEncoder().encode("doedoe"));
	// }
}
