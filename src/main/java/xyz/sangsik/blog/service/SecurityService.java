package xyz.sangsik.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import xyz.sangsik.blog.web.security.CustomUserDetailsService;

@Service
public class SecurityService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailsService userDetailsService;

    public void autoLogin(String name, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(name);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        if (auth.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }
}
