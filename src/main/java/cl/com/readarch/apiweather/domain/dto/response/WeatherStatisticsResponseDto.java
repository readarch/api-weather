package cl.com.readarch.apiweather.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto Class WeatherStatisticsResponseDto.
 *
 * @author readarch
 */
@Data
@NoArgsConstructor
public class WeatherStatisticsResponseDto {

  @JsonProperty("temperature")
  private StatisticsResponseDto temperature;

  @JsonProperty("precipitation")
  private StatisticsResponseDto precipitation;

  @JsonProperty("wind")
  private StatisticsResponseDto wind;

  @JsonProperty("rh")
  private StatisticsResponseDto rh;

  @JsonProperty("atmospheric_pressure")
  private StatisticsResponseDto atmosphericPressure;

  @JsonProperty("cloudiness")
  private StatisticsResponseDto cloudiness;
}
