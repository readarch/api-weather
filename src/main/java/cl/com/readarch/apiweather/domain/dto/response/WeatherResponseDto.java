package cl.com.readarch.apiweather.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto Class WeatherResponseDto.
 *
 * @author readarch
 */
@Data
@NoArgsConstructor
public class WeatherResponseDto {
  @Schema(example = "1")
  @JsonProperty("id")
  private Integer id;

  @Schema(example = "2022")
  @JsonProperty("year")
  private Integer year;

  @Schema(example = "1")
  @JsonProperty("month")
  private Integer month;

  @Schema(example = "1")
  @JsonProperty("day")
  private Integer day;

  @Schema(example = "CHILE")
  @JsonProperty("country")
  private String country;

  @Schema(example = "SANTIAGO")
  @JsonProperty("city")
  private String city;

  @Schema(example = "32 ºC")
  @JsonProperty("temperature")
  private String temperature;

  @Schema(example = "2.4 mm")
  @JsonProperty("precipitation")
  private String precipitation;

  @Schema(example = "12 Km/h")
  @JsonProperty("wind")
  private String wind;

  @Schema(example = "65 %")
  @JsonProperty("rh")
  private String rh;

  @Schema(example = "1035 hPa")
  @JsonProperty("atmospheric_pressure")
  private String atmosphericPressure;

  @Schema(example = "2 octas")
  @JsonProperty("cloudiness")
  private String cloudiness;
}
