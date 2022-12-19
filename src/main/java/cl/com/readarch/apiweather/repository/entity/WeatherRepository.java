package cl.com.readarch.apiweather.repository.entity;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository Class WeatherRepository.
 *
 * @author readarch
 */
@EnableScan
public interface WeatherRepository extends CrudRepository<WeatherEntity, String> {
}
