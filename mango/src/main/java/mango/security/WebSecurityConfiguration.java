package mango.security;

import mango.security.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration {
	@Autowired
	private JwtRequestFilter jwtRequestFilter;



	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.cors().and();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.csrf().disable().authorizeRequests()   // csrf -> disabled, posto JWT obradjuje zastitu od CSRF napada
				.antMatchers("/passenger").permitAll()
				.anyRequest().permitAll();// sve ostalo mora da bude autentifikovano
				 // ne koristimo HttpSession i kukije
		http.antMatcher("/user/login").authorizeRequests().anyRequest();
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); //JWT procesiramo pre autentikacije
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
}