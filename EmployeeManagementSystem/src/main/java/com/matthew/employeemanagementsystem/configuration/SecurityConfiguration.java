package com.matthew.employeemanagementsystem.configuration;

import com.matthew.employeemanagementsystem.filter.AuthenticationFilter;
import com.matthew.employeemanagementsystem.filter.AuthorizationFilter;
import com.matthew.employeemanagementsystem.service.jwt.AuthenticationService;
import com.matthew.employeemanagementsystem.service.jwt.AuthorizationService;
import com.matthew.employeemanagementsystem.service.user.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsService;
    private final SuffixConfiguration suffixConfiguration;
    private final AuthorizationService authorizationService;
    private final AuthenticationService authenticationService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(suffixConfiguration.bCryptPasswordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().cacheControl();
        http.csrf().disable();
        http.cors();
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/users/login").permitAll()
                .antMatchers("/api/users/register").permitAll()
                .antMatchers("/api/users/refreshToken").permitAll()
                .and()
                .formLogin()
                .loginPage("/api/users/login")
                .successForwardUrl("/home")
                .defaultSuccessUrl("/home", true)
                .and()
                .rememberMe()
                .rememberMeCookieName("remember")
                .tokenValiditySeconds(86400);
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManagerBean(), authenticationService);
        authenticationFilter.setFilterProcessesUrl("/api/users/login");
        http
                .addFilter(authenticationFilter)
                .addFilterBefore(new AuthorizationFilter(authorizationService), UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
