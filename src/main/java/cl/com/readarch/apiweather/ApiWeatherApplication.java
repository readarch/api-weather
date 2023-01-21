package cl.com.readarch.apiweather;

import cl.com.readarch.apiweather.repository.entity.WeatherRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * Main class ApiWeatherApplication.
 *
 * @author readarch
 */
@SpringBootApplication
@EnableDynamoDBRepositories(
    includeFilters = {
      @ComponentScan.Filter(
          type = FilterType.ASSIGNABLE_TYPE,
          classes = {WeatherRepository.class})
    })
@OpenAPIDefinition(
    info =
        @Info(
            title = "Service Weathers Microservice API",
            version = "1.0.0",
            description = "Microservice Weathers - Readarch Project"))
public class ApiWeatherApplication extends SpringBootServletInitializer {
  public static void main(String[] args) {
    SpringApplication.run(ApiWeatherApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(ApiWeatherApplication.class);
  }

}
