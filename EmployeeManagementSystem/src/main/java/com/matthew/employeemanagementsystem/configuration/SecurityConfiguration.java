package com.matthew.employeemanagementsystem.configuration;

import com.matthew.employeemanagementsystem.filter.AuthenticationFilter;
import com.matthew.employeemanagementsystem.filter.AuthorizationFilter;
import com.matthew.employeemanagementsystem.service.user.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsService;
    private final SuffixConfiguration suffixConfiguration;

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
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .successForwardUrl("/home")
                .defaultSuccessUrl("/home", true)
                .and()
                .rememberMe()
                .rememberMeCookieName("remember")
                .tokenValiditySeconds(86400)
                .and()
                .addFilter(new AuthenticationFilter(authenticationManagerBean()))
                .addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests().antMatchers("/api/department/delete/**").hasAnyAuthority("[ROLE_ADMIN]");
    }
}
