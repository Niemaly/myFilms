package com.Szumski.myFilms.configuration;

import com.Szumski.myFilms.repository.MovieRepository;
import com.Szumski.myFilms.service.UserDetailsServiceImplementation;
import com.Szumski.myFilms.service.UserMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImplementation userDetailsServiceImplementation;
    private MovieRepository movieRepository;
    private UserMoviesService userMoviesService;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImplementation userDetailsServiceImplementation, MovieRepository movieRepository, UserMoviesService userMoviesService ) {
        this.userDetailsServiceImplementation = userDetailsServiceImplementation;
        this.userMoviesService = userMoviesService;
        this.movieRepository = movieRepository;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/userDetails").hasAuthority("USER")
                .antMatchers("/filmsWatched").hasAuthority("USER")
                .antMatchers("/filmsToWatch").hasAuthority("USER")
                .antMatchers("/search").permitAll()
                .anyRequest().permitAll()
                .and().formLogin().permitAll()
                .and().csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.inMemoryAuthentication().withUser(new User("user", passwordEncoder().encode("user"), Collections.singleton(new SimpleGrantedAuthority("USER"))));
        auth.userDetailsService(userDetailsServiceImplementation);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*","/", "/userDetails", "/filmsWatched"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


}
