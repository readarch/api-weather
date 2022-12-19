package cl.com.readarch.apiweather.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto Class StatisticsResponseDto.
 *
 * @author readarch
 */
@Data
@NoArgsConstructor
public class StatisticsResponseDto {

  @Schema(example = "10.0")
  @JsonProperty("minimum")
  private double minimum;

  @Schema(example = "40.0")
  @JsonProperty("maximum")
  private double maximum;

  @Schema(example = "62")
  @JsonProperty("count")
  private long count;

  @Schema(example = "1614.0")
  @JsonProperty("sum")
  private double sum;

  @Schema(example = "26.032258064516128")
  @JsonProperty("average")
  private double average;

}
