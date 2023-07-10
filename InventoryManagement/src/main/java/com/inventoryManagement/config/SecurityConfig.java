package com.inventoryManagement.config;

import com.inventoryManagement.services.UserUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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
@Setter
public class SecurityConfig{

    private final JwtAuthenticationFilter filter;



    @Autowired
    private UserUserDetailsService userDetailsService;



    @Bean
    public UserDetailsService service(PasswordEncoder encoder) {
        return new UserUserDetailsService();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/auth/user/login", "auth/admin/login","/auth/token", "/auth/validate", "/auth/admin/login").permitAll()
                )
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/user/{adminUser}/usr", "/cart","/cart/{cartId}/tp").permitAll()
                )

                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/user/**").permitAll()
                )
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/item/**","/del/{id}").hasRole("ADMIN")
                )

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                )
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);


        return http.build();

    }



//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager());
//        filter.setFilter(this.filter);
//        return filter;
//    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public DaoAuthenticationProvider userAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

//    @Bean
//    public DaoAuthenticationProvider adminAuthenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(adminDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }

//    @Bean
//    public AuthenticationManager authenticationManager() {
//        List<AuthenticationProvider> authenticationProviders = new ArrayList<>();
//        authenticationProviders.add(userAuthenticationProvider());
//        return new ProviderManager(authenticationProviders);
//    }
//
}
