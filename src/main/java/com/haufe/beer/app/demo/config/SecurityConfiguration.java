package com.haufe.beer.app.demo.config;

import com.haufe.beer.app.demo.model.Roles;
import com.haufe.beer.app.demo.service.impl.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/console/**").permitAll()
                .antMatchers("/api/v1/providers/provider").hasAnyRole(Roles.ADMIN.getValue(), Roles.MANUFACTURER.getValue())
                .antMatchers("/api/v1/providers/{\\d+}").hasAnyRole(Roles.ADMIN.getValue(), Roles.MANUFACTURER.getValue())
                .antMatchers("/api/v1/providers").hasRole(Roles.ADMIN.getValue())
                .anyRequest().fullyAuthenticated()
                .and().httpBasic();

        http.csrf().disable();
        http.headers().frameOptions().disable();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("AdminPass123!")).roles(Roles.ADMIN.getValue())
                .and()
                .withUser("manufacturer1").password(passwordEncoder().encode("FirstPass123!")).roles(Roles.MANUFACTURER.getValue())
                .and()
                .withUser("manufacturer2").password(passwordEncoder().encode("SecondPass123!")).roles(Roles.MANUFACTURER.getValue());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
