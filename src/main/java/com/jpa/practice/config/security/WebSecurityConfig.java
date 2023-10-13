package com.jpa.practice.config.security;

import com.jpa.practice.config.security.auth.CustomOauth2UserService;
import com.jpa.practice.utils.jwt.JwtAuthenticateFilter;
import com.jpa.practice.utils.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOauth2UserService customOauth2UserService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/hi").permitAll()
                .antMatchers("/test").authenticated()
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(new JwtAuthenticateFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);

                /*.and()
                        .oauth2Login()
                                .userInfoEndpoint().userService(customOauth2UserService);
*/
        /*http
                .*/

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}