package cl.com.readarch.apiweather.domain.service;

import cl.com.readarch.apiweather.domain.dto.response.WeatherResponseDto;
import cl.com.readarch.apiweather.domain.dto.response.WeatherStatisticsResponseDto;
import java.util.List;

/**
 * Interface WeatherService.
 *
 * @author readarch
 */
public interface WeatherService {
  List<WeatherResponseDto> getWeathers(Integer year, Integer month, Integer day, String country, String city);
  WeatherStatisticsResponseDto getWeathersSummaryStatistics(Integer year, Integer month, Integer day, String country, String city);
  WeatherStatisticsResponseDto getWeathersReduce(Integer year, Integer month, Integer day, String country, String city);
  WeatherStatisticsResponseDto getWeathersFlatMap(String country, String firstCity, String secondCity);
}
