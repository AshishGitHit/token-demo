package com.example.jwt.token.demo.service;

import com.example.jwt.token.demo.entity.JwtRequest;
import com.example.jwt.token.demo.entity.JwtResponse;
import com.example.jwt.token.demo.entity.MyUser;
import com.example.jwt.token.demo.repo.UserRepo;
import com.example.jwt.token.demo.util.JwtUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepo;

    public JwtResponse createJwtToken(@NotNull JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userName, userPassword);

        final UserDetails userDetails = loadUserByUsername(userName);

        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        MyUser myUser = userRepo.findById(userName).get();

        return new JwtResponse(myUser, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = userRepo.findById(username).get();

        if (myUser != null) {
            return new User(myUser.getUserName(), myUser.getUserPassword(), getAuthorities(myUser));
        } else {
            throw new UsernameNotFoundException("Username is not valid");
        }
    }

    private @NotNull Set getAuthorities(@NotNull MyUser myUser) {
        Set authorities = new HashSet();

        myUser.getMyRole().forEach(myRole -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+myRole.getRoleName()));
        });
        return authorities;
    }

    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new Exception("User is diabled");
        } catch (BadCredentialsException e) {
            throw new Exception("Bad credentials from user");
        }
    }

}
