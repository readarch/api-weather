package cl.com.readarch.apiweather.crosscutting.security;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

/**
 * Class ApiKeyAuthFilter.
 * Filter responsible for getting the api key off of incoming requests that need to be authorized.
 * @author readarch
 */
public class ApiKeyAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

  private final String headerName;

  public ApiKeyAuthFilter(final String headerName) {
    this.headerName = headerName;
  }

  @Override
  protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
    return request.getHeader(headerName);
  }

  @Override
  protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
    // No credentials when using API key
    return null;
  }
}
