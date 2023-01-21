package cl.com.readarch.apiweather.controller;

import cl.com.readarch.apiweather.crosscutting.util.Constant;
import cl.com.readarch.apiweather.domain.dto.response.WeatherResponseDto;
import cl.com.readarch.apiweather.domain.service.WeatherDummyService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller Class WeatherDummyController.
 *
 * @author readarch
 */
@Tag(name = "WeatherDummyController", description = "Weather Dummy handler " +
    "class")
@RestController
@RequestMapping("/v1.0/weather-dummy")
@RequiredArgsConstructor
public class WeatherDummyController {

  private final WeatherDummyService weatherDummyService;

  /**
   * This endpoint will return weathers dummy according to the provided query
   * parameter like year, month, day, country and city.
   *
   * @return {@link List} of {@link WeatherResponseDto}
   */
  @GetMapping("/weathers")
  @Operation(
      summary = "Get weathers dummy by year, month, day, country and city",
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
            description = "Satisfactory obtaining of weathers dummy.",
            content =
                @Content(
                    array =
                        @ArraySchema(schema = @Schema(implementation = WeatherResponseDto.class)))),
        @ApiResponse(
            responseCode = "400",
            description = "No weather dummy information was found for the " +
                "search parameters.",
            content =
                @Content(
                    array =
                        @ArraySchema(schema = @Schema(implementation = WeatherResponseDto.class))))
      })
  public List<WeatherResponseDto> getWeathersDummy(
      @RequestParam(name = "year", required = false) Integer year,
      @RequestParam(name = "month", required = false) Integer month,
      @RequestParam(name = "day", required = false) Integer day,
      @RequestParam(name = "country", required = false) String country,
      @RequestParam(name = "city", required = false) String city) throws JsonProcessingException {
    return weatherDummyService.getWeathersDummy(year, month, day, country, city);
  }
}
