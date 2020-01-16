package app.prit.api.infrastructure.config;

import app.prit.api.infrastructure.core.port.application.UserApplicationPort;
import app.prit.api.infrastructure.security.AuthenticationRequestFilter;
import app.prit.api.infrastructure.security.LoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserApplicationPort userApplication;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public WebSecurityConfig(UserApplicationPort userApplication, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userApplication = userApplication;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.cors().and().csrf().ignoringAntMatchers("/h2/**","/favicon.ico").disable().authorizeRequests()
                .antMatchers("/h2/**","/favicon.ico","/v2/api-docs").permitAll()
                .antMatchers(HttpMethod.POST, "/api/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new LoginFilter(authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new AuthenticationRequestFilter(authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().headers().frameOptions().sameOrigin();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userApplication).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

}
