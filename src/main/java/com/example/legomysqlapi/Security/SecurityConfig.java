package com.example.legomysqlapi.Security;

import com.example.legomysqlapi.Security.Filter.CustomAuthenticationFilter;
import com.example.legomysqlapi.Security.Filter.CustomAuthorizationFilter;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static String secretKey = "LEGO";

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/auth/login"); //setting login path

        //Enable cors headers
        /*http.cors().configurationSource(request ->
                new CorsConfiguration().applyPermitDefaultValues()
        );*/
        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        /* PERMIT ALL */
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/auth/token/refresh").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/auth/login", "/api/auth/register").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/lego/category").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/lego/*").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/lego").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/lego/review/*").permitAll();
        /* PERMIT USERS */
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/lego/review").hasAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/lego/review").hasAuthority("ROLE_USER");

        /* PERMIT ADMINS */
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/lego/category").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/lego").hasAuthority("ROLE_ADMIN");

        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
