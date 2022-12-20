package cl.com.readarch.apiweather.domain.service.impl;

import cl.com.readarch.apiweather.crosscutting.util.Util;
import cl.com.readarch.apiweather.domain.dto.response.StatisticsResponseDto;
import cl.com.readarch.apiweather.domain.dto.response.WeatherResponseDto;
import cl.com.readarch.apiweather.domain.dto.response.WeatherStatisticsResponseDto;
import cl.com.readarch.apiweather.domain.mapper.WeatherMapper;
import cl.com.readarch.apiweather.domain.service.WeatherService;
import cl.com.readarch.apiweather.repository.entity.WeatherEntity;
import cl.com.readarch.apiweather.repository.WeatherRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service Implementation Class WeatherServiceImpl.
 *
 * @author readarch
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

  private final WeatherMapper weatherMapper;

  private final WeatherRepository weatherRepository;

  @Override
  public List<WeatherResponseDto> getWeathers(Integer year, Integer month, Integer day, String country, String city) {
    List<WeatherEntity> listWeatherEntity = getListWeatherEntity(year, month, day, country, city);

    return listWeatherEntity.stream().map(weatherMapper::toResponseDto).collect(Collectors.toList());
  }

  @Override
  public WeatherStatisticsResponseDto getWeathersSummaryStatistics(Integer year, Integer month, Integer day, String country, String city) {
    List<WeatherEntity> listWeatherEntity = getListWeatherEntity(year, month, day, country, city);

    return getWeatherStatisticsResponseDtoWithSummaryStatistics(listWeatherEntity);
  }

  @Override
  public WeatherStatisticsResponseDto getWeathersReduce(Integer year, Integer month, Integer day, String country, String city) {
    List<WeatherEntity> listWeatherEntity = getListWeatherEntity(year, month, day, country, city);

    return getWeatherStatisticsResponseDtoWithReduce(listWeatherEntity);
  }

  @Override
  public  WeatherStatisticsResponseDto getWeathersFlatMap(String country, String firstCity, String secondCity) {
    List<WeatherEntity> lstFirstWeatherEntity = getListWeatherEntity(null, null, null, country, firstCity);
    List<WeatherEntity> lstSecondWeatherEntity = getListWeatherEntity(null, null, null, country, secondCity);

    List<List<WeatherEntity>> lstWeathersEntity = new ArrayList<>();

    lstWeathersEntity.add(lstFirstWeatherEntity);
    lstWeathersEntity.add(lstSecondWeatherEntity);

    Supplier<Stream<WeatherEntity>> supplierStreamWeatherEntity =
        () -> lstWeathersEntity.stream() // Stream<List<WeatherEntity>>
        .flatMap(Collection::stream); // Stream<WeatherEntity>

    List<WeatherEntity> listWeatherEntity = Util.getListFromStream(supplierStreamWeatherEntity.get());

    return getWeatherStatisticsResponseDtoWithSummaryStatistics(listWeatherEntity);
  }

  private List<WeatherEntity> getListWeatherEntity(Integer year,
                                                   Integer month,
                                                   Integer day,
                                                   String country,
                                                   String city) {
    log.info("Ingresa al método getListWeatherEntity year = {}, month = {}, day = {}, country = {}, city = {}", year, month, day, country, city);

    Stream<WeatherEntity> streamWeatherEntity =
        StreamSupport.stream(weatherRepository.findAll().spliterator(),
        false)
        .filter(weatherEntity -> !Objects.nonNull(year) || weatherEntity.getYear().equals(year))
        .filter(weatherEntity -> !Objects.nonNull(month) || weatherEntity.getMonth().equals(month))
        .filter(weatherEntity -> !Objects.nonNull(day) || weatherEntity.getDay().equals(day))
        .filter(weatherEntity -> !Objects.nonNull(country) || weatherEntity.getCountry().equals(country))
        .filter(weatherEntity -> !Objects.nonNull(city) || weatherEntity.getCity().equals(city))
        .sorted(Comparator.comparing(WeatherEntity::getId));

    return Util.getListFromStream(streamWeatherEntity);
  }

  private WeatherStatisticsResponseDto getWeatherStatisticsResponseDtoWithSummaryStatistics(List<WeatherEntity> listWeatherEntity) {
    WeatherStatisticsResponseDto weatherStatisticsResponseDto = new WeatherStatisticsResponseDto();
    weatherStatisticsResponseDto.setTemperature(getSummaryStatisticsResponseDto(listWeatherEntity.stream(), WeatherEntity::getTemperature));
    weatherStatisticsResponseDto.setPrecipitation(getSummaryStatisticsResponseDto(listWeatherEntity.stream(), WeatherEntity::getPrecipitation));
    weatherStatisticsResponseDto.setWind(getSummaryStatisticsResponseDto(listWeatherEntity.stream(), WeatherEntity::getWind));
    weatherStatisticsResponseDto.setRh(getSummaryStatisticsResponseDto(listWeatherEntity.stream(), WeatherEntity::getRh));
    weatherStatisticsResponseDto.setAtmosphericPressure(getSummaryStatisticsResponseDto(listWeatherEntity.stream(), WeatherEntity::getAtmosphericPressure));
    weatherStatisticsResponseDto.setCloudiness(getSummaryStatisticsResponseDto(listWeatherEntity.stream(), WeatherEntity::getCloudiness));

    return weatherStatisticsResponseDto;
  }

  private WeatherStatisticsResponseDto getWeatherStatisticsResponseDtoWithReduce(List<WeatherEntity> listWeatherEntity) {
    WeatherStatisticsResponseDto weatherStatisticsResponseDto = new WeatherStatisticsResponseDto();
    weatherStatisticsResponseDto.setTemperature(getReduceResponseDto(listWeatherEntity, WeatherEntity::getTemperature));
    weatherStatisticsResponseDto.setPrecipitation(getReduceResponseDto(listWeatherEntity, WeatherEntity::getPrecipitation));
    weatherStatisticsResponseDto.setWind(getReduceResponseDto(listWeatherEntity, WeatherEntity::getWind));
    weatherStatisticsResponseDto.setRh(getReduceResponseDto(listWeatherEntity, WeatherEntity::getRh));
    weatherStatisticsResponseDto.setAtmosphericPressure(getReduceResponseDto(listWeatherEntity, WeatherEntity::getAtmosphericPressure));
    weatherStatisticsResponseDto.setCloudiness(getReduceResponseDto(listWeatherEntity, WeatherEntity::getCloudiness));

    return weatherStatisticsResponseDto;
  }

  private StatisticsResponseDto getSummaryStatisticsResponseDto(Stream<WeatherEntity> streamWeatherEntity, ToDoubleFunction<WeatherEntity> function) {
    DoubleSummaryStatistics statistics = streamWeatherEntity.mapToDouble(function).summaryStatistics();
    StatisticsResponseDto statisticsResponseDto = new StatisticsResponseDto();
    statisticsResponseDto.setMinimum(statistics.getMin());
    statisticsResponseDto.setMaximum(statistics.getMax());
    statisticsResponseDto.setCount(statistics.getCount());
    statisticsResponseDto.setSum(statistics.getSum());
    statisticsResponseDto.setAverage(statistics.getAverage());

    return statisticsResponseDto;
  }

  private StatisticsResponseDto getReduceResponseDto(List<WeatherEntity> listWeatherEntity, ToDoubleFunction<WeatherEntity> function) {
    StatisticsResponseDto statisticsResponseDto = new StatisticsResponseDto();
    OptionalDouble opMinimunDouble = listWeatherEntity.stream().mapToDouble(function).reduce((x, y) -> Double.compare(x, y) <= 0  ? x : y);
    statisticsResponseDto.setMinimum(opMinimunDouble.isPresent() ? opMinimunDouble.getAsDouble() : 0d);

    statisticsResponseDto.setMaximum(listWeatherEntity.stream().mapToDouble(function).reduce(0, Double::max));

    long count = (long) listWeatherEntity.stream().mapToDouble(function).reduce(0, (element1, element2) -> element1 + 1);
    statisticsResponseDto.setCount(count);

    double sum = listWeatherEntity.stream().mapToDouble(function).reduce(0, Double::sum);
    statisticsResponseDto.setSum(sum);

    statisticsResponseDto.setAverage(sum / count);

    return statisticsResponseDto;
  }
}
