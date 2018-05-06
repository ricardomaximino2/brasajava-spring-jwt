package com.brasajava.basicjwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.brasajava.basicjwt.filter.JwtAuthorizationTokenFilter;
import com.brasajava.basicjwt.service.UserService;
import com.brasajava.basicjwt.util.JwtTokenUtil;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
    private UnAuthorizedHandler unauthorizedHandler;
	
	@Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.route.authentication.path}")
    private String authenticationPath;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// we don't need CSRF because our token is invulnerable
		http.csrf()
			.disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            // don't create session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authorizeRequests()
				.antMatchers("/public/**").permitAll() // the permitAll is ignored here it is catch by the configure(WebSecurity web) method
				.antMatchers("/security/**").hasAnyRole("ADMIN","USER")
				.antMatchers("/admin/**").hasRole("ADMIN")
	            .antMatchers("/h2-console/**/**").permitAll()// Un-secure H2 Database  the permitAll is ignored here it is catch by the configure(WebSecurity web) method
	            .antMatchers("/auth/**").permitAll() // the permitAll is ignored here it is catch by the configure(WebSecurity web) method
	            .anyRequest().authenticated();
		
		// Custom JWT based security filter
        JwtAuthorizationTokenFilter authenticationTokenFilter = new JwtAuthorizationTokenFilter(userDetailsService(), jwtTokenUtil, tokenHeader);
        http
            .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        http
            .headers()
            .frameOptions().sameOrigin()  // required to set for H2 else H2 Console will be blank.
            .cacheControl();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        // AuthenticationTokenFilter will ignore the below paths
        web
            .ignoring()
            .antMatchers(HttpMethod.POST, authenticationPath)

            // allow anonymous resource requests
            .and()
            .ignoring()
            .antMatchers(
                HttpMethod.GET,
                "/",
                "/*.html",
                "/favicon.ico",
                "/**/*.html",
                "/**/*.css",
                "/public/**",
                "/**/*.js"
            ) 

            // Un-secure H2 Database (for testing purposes, H2 console shouldn't be unprotected in production)
            .and()
            .ignoring()
            .antMatchers("/h2-console/**/**");
    }
}
