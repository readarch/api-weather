package cl.com.readarch.apiweather.crosscutting.security;

import cl.com.readarch.apiweather.repository.UserRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Class BasicAuthWebSecurityConfiguration.
 * We can rewrite the above basic auth configuration in the latest versions.
 * @author readarch
 */
@Configuration
public class BasicAuthWebSecurityConfiguration {

  private final AppBasicAuthenticationEntryPoint authenticationEntryPoint;

  private final UserRepository userRepository;

  @Autowired
  public BasicAuthWebSecurityConfiguration(AppBasicAuthenticationEntryPoint authenticationEntryPoint,
                                           UserRepository userRepository) {
    this.authenticationEntryPoint = authenticationEntryPoint;
    this.userRepository = userRepository;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/public").permitAll()
        .anyRequest().authenticated()
        .and()
        .httpBasic()
        .authenticationEntryPoint(authenticationEntryPoint);
    return http.build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    ArrayList<UserDetails> users = new ArrayList<>();
    userRepository.findAll().forEach(userEntity -> {
      UserDetails userDetails = User
          .withUsername(userEntity.getUsername())
          .password(userEntity.getPasswordBcrypt())
          .roles("USER")
          .build();
      users.add(userDetails);
    });
    return new InMemoryUserDetailsManager(users);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(8);
  }
}
