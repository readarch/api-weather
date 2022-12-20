package cl.com.readarch.apiweather.domain.dto.request;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class JwtRequestDto.
 * This class is required for storing the username and password we receive from the client.
 * @author readarch
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class JwtRequestDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private String username;
  private String password;

}
