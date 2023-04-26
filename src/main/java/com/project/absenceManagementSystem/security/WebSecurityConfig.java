package com.project.absenceManagementSystem.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.project.absenceManagementSystem.services.UserService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {
	
	private static final String[] AUTH_WHITELIST = {
			"/registration**",
            "/js/**",
            "/bootstrap/**",
            "/css/**",
            "/img/**"
	};

	@Autowired
	private UserService userService;
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests((requests) -> {
	            try {
	                requests
	                    .requestMatchers("/login**","/registration**").anonymous()
	                    .requestMatchers(AUTH_WHITELIST).permitAll()
	                    .anyRequest().authenticated();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        })
	        .formLogin()
	            .loginPage("/login")
	            .and()
	        .logout()
	            .invalidateHttpSession(true)
	            .clearAuthentication(true)
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            .logoutSuccessUrl("/login?logout")
	            .permitAll()
	            .and()
	        .httpBasic();
	 
	    return http.build();
	}


}