package com.diaza.habitracker.services;


import com.diaza.habitracker.config.JwtUtil;
import com.diaza.habitracker.dao.IUserDao;
import com.diaza.habitracker.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final IUserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


        @Autowired
        private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    public String register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        userDao.save(user);
        return jwtUtil.generateToken(email);
    }

    public String login(String email, String password) {
        User user = userDao.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

    if (!passwordEncoder.matches(password, user.getPassword())) {
        throw new RuntimeException("Invalid password");
    }
    return jwtUtil.generateToken(email);
    }
    public String authenticate(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return jwtUtil.generateToken(userDetails.getUsername());    }
}
