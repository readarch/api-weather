package cl.com.readarch.apiweather.domain.mapper;

import cl.com.readarch.apiweather.domain.dto.response.WeatherResponseDto;
import cl.com.readarch.apiweather.repository.entity.WeatherEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper Class WeatherMapper.
 *
 * @author readarch
 */
@Mapper(componentModel = "spring")
public interface WeatherMapper {
  @Mapping(target = "temperature", expression = "java(weatherEntity.getTemperature() + weatherEntity.getTemperatureScale())")
  @Mapping(target = "precipitation", expression = "java(weatherEntity.getPrecipitation() + weatherEntity.getPrecipitationScale())")
  @Mapping(target = "wind", expression = "java(weatherEntity.getWind() + weatherEntity.getWindScale())")
  @Mapping(target = "rh", expression = "java(weatherEntity.getRh() + weatherEntity.getRhScale())")
  @Mapping(target = "atmosphericPressure", expression = "java(weatherEntity.getAtmosphericPressure() + weatherEntity.getAtmosphericPressureScale())")
  @Mapping(target = "cloudiness", expression = "java(weatherEntity.getCloudiness() + weatherEntity.getCloudinessScale())")
  WeatherResponseDto toResponseDto(WeatherEntity weatherEntity);
}
