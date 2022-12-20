package cl.com.readarch.apiweather.repository;

import cl.com.readarch.apiweather.repository.entity.UserEntity;
import java.util.Optional;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository Class UserRepository.
 *
 * @author readarch
 */
@EnableScan
public interface UserRepository extends CrudRepository<UserEntity, String> {

  Optional<UserEntity> findByPassword(String password);

}