package com.archiiro.app.Core.Other;

import com.archiiro.app.Core.Domain.Role;
import com.archiiro.app.Core.Domain.User;
import com.archiiro.app.Core.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
public class ProcessUtils {
    @Autowired
    public static UserRepository userSupport;

    public static String getHashPassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isUserInRole(String username, String role) {
        if(username != null) {
            User user = userSupport.getUser(username);
            if(user != null && user.getRoles() != null && user.getRoles().size() > 0) {
                for(Role getRole : user.getRoles()) {
                    if(getRole.getName().equals(role)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean passwordsMatch(String encryptedPassword, String plainPassword) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(plainPassword, encryptedPassword);
    }

    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return isNotNull(authentication) && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken);
    }
    public static User getCurrentUser() {
        if (!isAuthenticated()) {
            return null;
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return (User)authentication.getPrincipal();
        }
    }
}
