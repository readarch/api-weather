package cl.com.readarch.apiweather.crosscutting.security;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Class AppBasicAuthenticationEntryPoint.
 * The authentication entry points are used by the ExceptionTranslationFilter to commence authentication.
 * By default, the BasicAuthenticationEntryPoint returns a full page for a 401 Unauthorized response back to the client.
 * To customize the default authentication error page used by basic auth, we can extend the BasicAuthenticationEntryPoint class.
 * Here we can set the realm name and well as the error message sent back to the client.
 * @author readarch
 */
@Component
public class AppBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request,
                       HttpServletResponse response,
                       AuthenticationException authEx) throws IOException {

    response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    PrintWriter writer = response.getWriter();
    writer.println("HTTP Status 401 - " + authEx.getMessage());
  }

  @Override
  public void afterPropertiesSet() {
    setRealmName("READARCH");
    super.afterPropertiesSet();
  }
}
