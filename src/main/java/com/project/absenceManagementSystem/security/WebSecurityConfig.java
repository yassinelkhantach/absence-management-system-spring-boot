package com.project.absenceManagementSystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.project.absenceManagementSystem.services.security.UserService;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	private static final String[] AUTH_WHITELIST = {
			"/public/*",
            "/assets/**",
			"/js/**",
            "/bootstrap/**",
            "/css/**",
            "/img/**",
            "/login**",
			"/registration**"
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
	    .csrf().disable()
	        .authorizeHttpRequests((requests) -> {
	            try {
	                requests
                    	.requestMatchers(AUTH_WHITELIST).permitAll()
                    	.requestMatchers("/student/**").hasRole("STUDENT")
	                    .requestMatchers("/teacher/**").hasRole("TEACHER")
	                    .requestMatchers("/cadre-administrator/**").hasRole("CADRE_ADMINISTRATOR")
	                    .requestMatchers("/admin/**").hasRole("ADMINISTRATOR")
	                    .anyRequest().authenticated();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        })
	        .formLogin()
	            .loginPage("/login")
            .successHandler(new CustomAuthenticationSuccessHandler())
            .failureHandler(authenticationFailureHandler()) // configure the authentication failure handler
            .and()
	        .logout()
	            .invalidateHttpSession(true)
	            .clearAuthentication(true)
	            .deleteCookies("JSESSIONID")
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            .logoutSuccessUrl("/login")
	            .permitAll()
	            .and()
	            .rememberMe()
	            .and()
	        .httpBasic();
	 
	    return http.build();
	}
	
	@Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMINISTRATOR > ROLE_STUDENT\n"
            + "ROLE_ADMINISTRATOR > ROLE_TEACHER\n"
            + "ROLE_ADMINISTRATOR > ROLE_CADRE_ADMINISTRATOR");
        return roleHierarchy;
    }

    @Bean
    public DefaultMethodSecurityExpressionHandler expressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;
    }

    
    @Bean
    public CustomAuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }


    @Bean
    public MethodSecurityExpressionHandler createExpressionHandler() {
        return expressionHandler();
    }
	
	@Bean
	public LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}

}