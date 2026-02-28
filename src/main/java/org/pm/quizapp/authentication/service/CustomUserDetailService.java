package org.pm.quizapp.authentication.service;

import lombok.RequiredArgsConstructor;
import org.pm.quizapp.authentication.entity.User;
import org.pm.quizapp.authentication.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<User>user= userRepository.findByUsername(username);
       user.orElseThrow(()->new UsernameNotFoundException("user with this username :"+username+"is not found "));
       return new UserDetail(user.get());
    }
}
