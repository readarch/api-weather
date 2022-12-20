package cl.com.readarch.apiweather.domain.service.impl;

import cl.com.readarch.apiweather.repository.UserJwtRepository;
import cl.com.readarch.apiweather.repository.entity.UserJwtEntity;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Class JwtUserDetailsService.
 * JWTUserDetailsService implements the Spring Security UserDetailsService interface.
 * It overrides the loadUserByUsername for fetching user details from the database using the username.
 * The Spring Security Authentication Manager calls this method for getting the user details from the database when authenticating the user details provided
 * by the user.
 * @author readarch
 */
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

  private final UserJwtRepository userJwtRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserJwtEntity userJwtEntity = userJwtRepository.findByUsername(username).orElseThrow(() -> {
      throw new UsernameNotFoundException("User not found with username: " + username);
    });

    return new User(userJwtEntity.getUsername(), userJwtEntity.getPasswordBcrypt(), new ArrayList<>());
  }

}