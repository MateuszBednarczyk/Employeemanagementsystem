package com.matthew.employeemanagementsystem.configuration;

import com.matthew.employeemanagementsystem.filter.AuthenticationFilter;
import com.matthew.employeemanagementsystem.filter.AuthorizationFilter;
import com.matthew.employeemanagementsystem.service.jwt.AuthenticationService;
import com.matthew.employeemanagementsystem.service.jwt.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class SecurityConfiguration {
    private final UserDetailsService userDetailsService;
    private final SuffixConfiguration suffixConfiguration;
    private final AuthorizationService authorizationService;
    private final AuthenticationService authenticationService;
    private String loginURL = "/api/users/login";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = authenticationManager(http.getSharedObject(AuthenticationConfiguration.class));
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager, authenticationService);
        authenticationFilter.setFilterProcessesUrl(loginURL);
        http.headers().cacheControl();
        http.csrf().disable();
        http.cors();
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, loginURL).permitAll()
                .antMatchers("/api/users/register").permitAll()
                .antMatchers("/api/users/refreshToken").permitAll()
                .and()
                .formLogin()
                .loginPage(loginURL)
                .and()
                .rememberMe()
                .rememberMeCookieName("remember")
                .tokenValiditySeconds(86400);
        http
                .addFilter(authenticationFilter)
                .addFilterBefore(new AuthorizationFilter(authorizationService), UsernamePasswordAuthenticationFilter.class);
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .authorizeRequests()
                .antMatchers("/api/department/add-moderator")
                .hasAnyAuthority("[ROLE_ADMIN]");

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}