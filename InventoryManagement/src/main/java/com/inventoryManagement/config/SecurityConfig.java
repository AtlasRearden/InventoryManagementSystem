package com.inventoryManagement.config;

import com.inventoryManagement.services.AdminAdminDetailsService;
import com.inventoryManagement.services.UserUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;
    @Bean
    public UserDetailsService userDetailsService(){


        return new UserUserDetailsService();
    }
    @Bean
    public UserDetailsService service(PasswordEncoder encoder){
        return new AdminAdminDetailsService();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors->cors.disable())
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/auth/login","/auth/token","/auth/validate").permitAll()
                )
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/main/{admin_id}/usr").permitAll()
                )
                .authorizeHttpRequests((auth)->auth
                        .requestMatchers("/main").permitAll()
                )
                .authorizeHttpRequests((auth)->auth
                        .requestMatchers("/user/**").authenticated()
                )
//                .authorizeHttpRequests((auth)->auth
//                        .requestMatchers("/main/")
//                )
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                );
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
