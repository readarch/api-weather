package cl.com.readarch.apiweather.crosscutting.security;

import cl.com.readarch.apiweather.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Class ApiKeyAuthManager.
 * Handles authenticating api keys against the DynamoDB database.
 * @author readarch
 */
@RequiredArgsConstructor
public class ApiKeyAuthManager implements AuthenticationManager {

  private final UserRepository userRepository;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String password = (String) authentication.getPrincipal();

    userRepository.findByPassword(password).orElseThrow(() -> {
      throw new BadCredentialsException("The API key was not found or not the expected value.");
    });

    authentication.setAuthenticated(true);
    return authentication;

  }

}
