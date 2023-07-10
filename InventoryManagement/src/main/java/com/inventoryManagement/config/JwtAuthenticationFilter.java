package com.inventoryManagement.config;

//import com.inventoryManagement.services.AdminAdminDetailsService;
import com.inventoryManagement.services.UserUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
////    private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
////    @Autowired
////    private JwtHelper jwtHelper;
////
////    @Autowired
////    private UserUserDetailsService userDetailsService;
////
////
////
////
////    @Override
////    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
////                                    FilterChain filterChain) throws ServletException, IOException {
////        String requestHeader = request.getHeader("Authorization");
////        String username = null;
////        String token = null;
////
////        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
////            token = requestHeader.substring(7);
////            try {
////                username = this.jwtHelper.getUsernameFromToken(token);
////            } catch (Exception e) {
////                logger.info("Exception while validating token: {}", e.getMessage());
////            }
////        } else {
////            logger.info("Invalid Header Value !!");
////        }
////
////        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
////            UserUserDetails userDetails = (UserUserDetails) this.userDetailsService.loadUserByUsername(username);
////            Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
////            if (validateToken) {
////                UsernamePasswordAuthenticationToken authentication =
////                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
////                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////                SecurityContextHolder.getContext().setAuthentication(authentication);
////            } else {
////                logger.info("Token validation failed for user: {}", username);
////            }
////        }
////
////        filterChain.doFilter(request, response);
////    }
//
//
//
//
//
//    private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
//
//    @Autowired
//    private JwtHelper jwtHelper;
//
//    @Autowired
//    private UserUserDetailsService userDetailsService;
////
////    @Autowired
////    private AdminAdminDetailsService adminDetailsService;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        String requestHeader = request.getHeader("Authorization");
//        String username = null;
//        String token = null;
//
//        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
//            token = requestHeader.substring(7);
//            try {
//                username = this.jwtHelper.getUsernameFromToken(token);
//            } catch (Exception e) {
//                logger.info("Exception while validating token: {}", e.getMessage());
//            }
//        } else {
//            logger.info("Invalid Header Value !!");
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails =  this.userDetailsService.loadUserByUsername(username);
////            UserDetails adminDetails = this.adminDetailsService.loadUserByUsername(username);
//            Boolean validateToken = this.jwtHelper.validateToken(token,userDetails);
//
//            if (validateToken) {
//                UsernamePasswordAuthenticationToken authentication;
//
////                if (adminDetails != null) {
////                    authentication = new UsernamePasswordAuthenticationToken(adminDetails, null, adminDetails.getAuthorities());
////                }
//                authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            } else {
//                logger.info("Token validation failed for user: {}", username);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }


//}

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {



    private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserUserDetailsService userDetailsService;
    //
//    @Autowired
//    private AdminAdminDetailsService adminDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String requestHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            token = requestHeader.substring(7);
            try {
                username = this.jwtHelper.getUsernameFromToken(token);
            } catch (Exception e) {
                logger.info("Exception while validating token: {}", e.getMessage());
            }
        } else {
            logger.info("Invalid Header Value !!");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails =  this.userDetailsService.loadUserByUsername(username);
//            UserDetails adminDetails = this.adminDetailsService.loadUserByUsername(username);
            Boolean validateToken = this.jwtHelper.validateToken(token,userDetails);

            if (validateToken) {
                UsernamePasswordAuthenticationToken authentication;

//                if (adminDetails != null) {
//                    authentication = new UsernamePasswordAuthenticationToken(adminDetails, null, adminDetails.getAuthorities());
//                }
                authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                logger.info("Token validation failed for user: {}", username);
            }
        }

        filterChain.doFilter(request, response);
    }






//    private JwtHelper jwtHelper;
//
//    private JwtAuthenticationFilter filter;
//
//    public void setFilter(JwtAuthenticationFilter filter) {
//        this.filter = filter;
//    }
//
//    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
//        super(new AntPathRequestMatcher("/auth/user/login","/auth/admin/login"));
//        this.jwtHelper = jwtHelper;
//        setAuthenticationManager(authenticationManager);
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            JwtRequest jwtRequest = new ObjectMapper().readValue(request.getInputStream(), JwtRequest.class);
//
//            return getAuthenticationManager().authenticate(
//                    new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword())
//            );
//        } catch (IOException e) {
//            throw new AuthenticationServiceException("Failed to parse authentication request", e);
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain chain,
//            Authentication authResult) throws IOException, ServletException {
//
//        User user = (User) authResult.getPrincipal();
//        boolean isAdmin = user.isAdmin();
//
//        String token = jwtHelper.generateToken(user.getEmail(), isAdmin);
//
//        response.addHeader("Authorization", "Bearer " + token);
//        response.getWriter().write(token);
//        response.getWriter().flush();
//    }
}
