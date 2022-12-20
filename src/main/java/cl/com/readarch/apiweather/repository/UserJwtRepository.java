package cl.com.readarch.apiweather.repository;

import cl.com.readarch.apiweather.repository.entity.UserJwtEntity;
import java.util.Optional;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository Class UserJwtRepository.
 *
 * @author readarch
 */
@EnableScan
public interface UserJwtRepository extends CrudRepository<UserJwtEntity, String> {

  Optional<UserJwtEntity> findByUsername(String username);

}
