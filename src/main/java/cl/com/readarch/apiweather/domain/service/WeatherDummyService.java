package cl.com.readarch.apiweather.domain.service;

import cl.com.readarch.apiweather.domain.dto.response.WeatherResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

/**
 * Interface WeatherDummyService.
 *
 * @author readarch
 */
public interface WeatherDummyService {
  List<WeatherResponseDto> getWeathersDummy(Integer year, Integer month, Integer day, String country, String city) throws JsonProcessingException;
}
