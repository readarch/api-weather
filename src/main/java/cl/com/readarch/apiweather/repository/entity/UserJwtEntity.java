package cl.com.readarch.apiweather.repository.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class UserJwtEntity.
 *
 * @author readarch
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "UsersJwt")
public class UserJwtEntity {
  @DynamoDBHashKey private Integer id;

  @DynamoDBAttribute private String username;

  @DynamoDBAttribute private String password;

  @DynamoDBAttribute private String passwordBcrypt;

}
