package cl.com.readarch.apiweather.domain.dto.response;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Class JwtResponseDto.
 * This is class is required for creating a response containing the JWT to be returned to the user.
 * @author readarch
 */
@AllArgsConstructor
@Getter
public class JwtResponseDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private final String jwttoken;

}
