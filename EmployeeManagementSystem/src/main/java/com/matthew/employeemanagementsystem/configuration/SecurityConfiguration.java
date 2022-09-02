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
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalAuthentication
class SecurityConfiguration {
    private final AuthorizationService authorizationService;
    private final AuthenticationService authenticationService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String roleAdmin = "[ROLE_ADMIN]";
        String roleSuperAdmin = "[ROLE_SUPERADMIN]";
        String loginURL = "/api/users/login";
        AuthenticationManager authenticationManager = authenticationManager(http.getSharedObject(AuthenticationConfiguration.class));
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager, authenticationService);
        authenticationFilter.setFilterProcessesUrl(loginURL);
        http.headers().cacheControl();
        http.csrf().disable();
        http.cors();
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, loginURL).permitAll()
                .antMatchers("/api/users/register").hasAnyAuthority(roleAdmin, roleSuperAdmin)
                .antMatchers("/api/users/refreshToken").permitAll()
                .antMatchers("/api/users/verify").permitAll();
        http
                .addFilter(authenticationFilter)
                .addFilterBefore(new AuthorizationFilter(authorizationService), UsernamePasswordAuthenticationFilter.class);
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .authorizeRequests()
                .antMatchers("/api/department/add-moderator").hasAnyAuthority(roleAdmin, roleSuperAdmin)
                .antMatchers("/api/department/add").hasAnyAuthority(roleAdmin, roleSuperAdmin);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}