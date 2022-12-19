package cl.com.readarch.apiweather.repository.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class WeatherEntity.
 *
 * @author readarch
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "Weathers")
public class WeatherEntity {
  @DynamoDBHashKey private Integer id;

  @DynamoDBAttribute private Integer year;

  @DynamoDBAttribute private Integer month;

  @DynamoDBAttribute private Integer day;

  @DynamoDBAttribute private String country;

  @DynamoDBAttribute private String city;

  @DynamoDBAttribute private Integer temperature;

  @DynamoDBAttribute private String temperatureScale;

  @DynamoDBAttribute private Double precipitation;

  @DynamoDBAttribute private String precipitationScale;

  @DynamoDBAttribute private Integer wind;

  @DynamoDBAttribute private String windScale;

  @DynamoDBAttribute private Integer rh;

  @DynamoDBAttribute private String rhScale;

  @DynamoDBAttribute private Integer atmosphericPressure;

  @DynamoDBAttribute private String atmosphericPressureScale;

  @DynamoDBAttribute private Integer cloudiness;

  @DynamoDBAttribute private String cloudinessScale;
}
