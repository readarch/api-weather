package cl.com.readarch.apiweather.crosscutting.security;

import cl.com.readarch.apiweather.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@Order(1)
@RequiredArgsConstructor
public class AuthConfiguration extends WebSecurityConfigurerAdapter {

  @Value("${api.key}")
  String apiKeyAuthHeaderName;

  private final UserRepository userRepository;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    ApiKeyAuthFilter filter = new ApiKeyAuthFilter(apiKeyAuthHeaderName);
    filter.setAuthenticationManager(new ApiKeyAuthManager(userRepository));

    http.antMatcher("/v1.0/weather/*").
        csrf().
        disable().
        sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
        and()
        .addFilter(filter)
        .authorizeRequests()
        .anyRequest()
        .authenticated();
  }
}
