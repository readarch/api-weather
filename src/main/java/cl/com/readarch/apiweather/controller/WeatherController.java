package cl.com.readarch.apiweather.controller;

import cl.com.readarch.apiweather.crosscutting.util.Constant;
import cl.com.readarch.apiweather.domain.dto.response.WeatherResponseDto;
import cl.com.readarch.apiweather.domain.dto.response.WeatherStatisticsResponseDto;
import cl.com.readarch.apiweather.domain.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller Class WeatherController.
 *
 * @author readarch
 */
@Slf4j
@Tag(name = "Weather", description = "Weather handler class")
@RestController
@RequestMapping("/v1.0/weather")
@RequiredArgsConstructor
public class WeatherController {

  private final WeatherService weatherService;

  /**
   * This endpoint will return weathers according to the provided query
   * parameter like year, month, day, country and city.
   *
   * @return {@link List} of {@link WeatherResponseDto}
   */
  @GetMapping("/weathers")
  @Operation(
      summary = "Get weathers by year, month, day, country and city",
      description = Constant.GET_WEATHERS,
      parameters = {
          @Parameter(
              name = "year",
              in = ParameterIn.QUERY,
              description = "Year consulted to obtain its weather",
              schema = @Schema(type = "int", example = "2022")),
          @Parameter(
              name = "month",
              in = ParameterIn.QUERY,
              description = "Month consulted to obtain its weather",
              schema = @Schema(type = "int", example = "1")),
          @Parameter(
              name = "day",
              in = ParameterIn.QUERY,
              description = "Day consulted to obtain its weather",
              schema = @Schema(type = "int", example = "1")),
          @Parameter(
              name = "country",
              in = ParameterIn.QUERY,
              description = "Country consulted to obtain its weather",
              schema = @Schema(type = "string", example = "CHILE")),
          @Parameter(
              name = "city",
              in = ParameterIn.QUERY,
              description = "City consulted to obtain its weather",
              schema = @Schema(type = "string", example = "SANTIAGO"))
      },
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Satisfactory obtaining of weathers.",
            content =
                @Content(
                    array =
                        @ArraySchema(schema = @Schema(implementation = WeatherResponseDto.class)))),
        @ApiResponse(
            responseCode = "400",
            description = "No weather information was found for the search parameters.",
            content =
                @Content(
                    array =
                        @ArraySchema(schema = @Schema(implementation = WeatherResponseDto.class))))
      })
  public List<WeatherResponseDto> getWeathers(
      @RequestParam(name = "year", required = false) Integer year,
      @RequestParam(name = "month", required = false) Integer month,
      @RequestParam(name = "day", required = false) Integer day,
      @RequestParam(name = "country", required = false) String country,
      @RequestParam(name = "city", required = false) String city) {
    return weatherService.getWeathers(year, month, day, country, city);
  }

  /**
   * This endpoint will return weathers statistics with summary statistics
   * according to the provided query parameter like year, month, day, country
   * and city.
   *
   * @return {@link List} of {@link WeatherStatisticsResponseDto}
   */
  @GetMapping("/summary-statistics")
  @Operation(
      summary = "Get weathers statistics with summary statistics by year, month, day, country and city",
      description = Constant.GET_SUMMARY_STATISTICS,
      parameters = {
          @Parameter(
              name = "year",
              in = ParameterIn.QUERY,
              description = "Year consulted to obtain its weather statistics",
              schema = @Schema(type = "int", example = "2022")),
          @Parameter(
              name = "month",
              in = ParameterIn.QUERY,
              description = "Month consulted to obtain its weather statistics",
              schema = @Schema(type = "int", example = "1")),
          @Parameter(
              name = "day",
              in = ParameterIn.QUERY,
              description = "Day consulted to obtain its weather statistics",
              schema = @Schema(type = "int", example = "1")),
          @Parameter(
              name = "country",
              in = ParameterIn.QUERY,
              description = "Country consulted to obtain its weather statistics",
              schema = @Schema(type = "string", example = "CHILE")),
          @Parameter(
              name = "city",
              in = ParameterIn.QUERY,
              description = "City consulted to obtain its weather statistics",
              schema = @Schema(type = "string", example = "SANTIAGO"))
      },
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Satisfactory obtaining of weathers statistics.",
              content =
              @Content(
                  array =
                  @ArraySchema(schema = @Schema(implementation = WeatherStatisticsResponseDto.class)))),
          @ApiResponse(
              responseCode = "400",
              description = "No weathers statistics information was found for the search parameters.",
              content =
              @Content(
                  array =
                  @ArraySchema(schema = @Schema(implementation = WeatherStatisticsResponseDto.class))))
      })
  public WeatherStatisticsResponseDto getWeathersSummaryStatistics(
      @RequestParam(name = "year", required = false) Integer year,
      @RequestParam(name = "month", required = false) Integer month,
      @RequestParam(name = "day", required = false) Integer day,
      @RequestParam(name = "country", required = false) String country,
      @RequestParam(name = "city", required = false) String city) {
    return weatherService.getWeathersSummaryStatistics(year, month, day, country, city);
  }

  /**
   * This endpoint will return weathers statistics with reduce
   * according to the provided query parameter like year, month, day, country
   * and city.
   *
   * @return {@link List} of {@link WeatherStatisticsResponseDto}
   */
  @GetMapping("/reduce")
  @Operation(
      summary = "Get weathers statistics with reduce by year, month, day, country and city",
      description = Constant.GET_REDUCE,
      parameters = {
          @Parameter(
              name = "year",
              in = ParameterIn.QUERY,
              description = "Year consulted to obtain its weather statistics",
              schema = @Schema(type = "int", example = "2022")),
          @Parameter(
              name = "month",
              in = ParameterIn.QUERY,
              description = "Month consulted to obtain its weather statistics",
              schema = @Schema(type = "int", example = "1")),
          @Parameter(
              name = "day",
              in = ParameterIn.QUERY,
              description = "Day consulted to obtain its weather statistics",
              schema = @Schema(type = "int", example = "1")),
          @Parameter(
              name = "country",
              in = ParameterIn.QUERY,
              description = "Country consulted to obtain its weather statistics",
              schema = @Schema(type = "string", example = "CHILE")),
          @Parameter(
              name = "city",
              in = ParameterIn.QUERY,
              description = "City consulted to obtain its weather statistics",
              schema = @Schema(type = "string", example = "SANTIAGO"))
      },
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Satisfactory obtaining of weathers statistics.",
              content =
              @Content(
                  array =
                  @ArraySchema(schema = @Schema(implementation = WeatherStatisticsResponseDto.class)))),
          @ApiResponse(
              responseCode = "400",
              description = "No weathers statistics information was found for the search parameters.",
              content =
              @Content(
                  array =
                  @ArraySchema(schema = @Schema(implementation = WeatherStatisticsResponseDto.class))))
      })
  public WeatherStatisticsResponseDto getWeathersReduce(
      @RequestParam(name = "year", required = false) Integer year,
      @RequestParam(name = "month", required = false) Integer month,
      @RequestParam(name = "day", required = false) Integer day,
      @RequestParam(name = "country", required = false) String country,
      @RequestParam(name = "city", required = false) String city) {
    return weatherService.getWeathersReduce(year, month, day, country, city);
  }

  /**
   * This endpoint will return weathers statistics using flatMap
   * according to the provided query parameter like country
   * and first_city and second_city.
   *
   * @return {@link List} of {@link WeatherStatisticsResponseDto}
   */
  @GetMapping("/flat-map")
  @Operation(
      summary = "Get weathers statistics using flatMap by country, first_city and second_city",
      description = Constant.GET_FLAT_MAP,
      parameters = {
          @Parameter(
              name = "country",
              in = ParameterIn.QUERY,
              description = "Country consulted to obtain its weather statistics",
              schema = @Schema(type = "string", example = "CHILE")),
          @Parameter(
              name = "first_city",
              in = ParameterIn.QUERY,
              description = "First City consulted to obtain its weather statistics with the other city",
              schema = @Schema(type = "string", example = "SANTIAGO")),
          @Parameter(
              name = "second_city",
              in = ParameterIn.QUERY,
              description = "Second City consulted to obtain its weather statistics with the other city",
              schema = @Schema(type = "string", example = "ANTOFAGASTA"))
      },
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Satisfactory obtaining of weathers statistics.",
              content =
              @Content(
                  array =
                  @ArraySchema(schema = @Schema(implementation = WeatherStatisticsResponseDto.class)))),
          @ApiResponse(
              responseCode = "400",
              description = "No weathers statistics information was found for the search parameters.",
              content =
              @Content(
                  array =
                  @ArraySchema(schema = @Schema(implementation = WeatherStatisticsResponseDto.class))))
      })
  public WeatherStatisticsResponseDto getWeathersFlatMap(
      @RequestParam(name = "country") String country,
      @RequestParam(name = "first_city") String firstCity,
      @RequestParam(name = "second_city") String secondCity) {
    return weatherService.getWeathersFlatMap(country, firstCity, secondCity);
  }
}
