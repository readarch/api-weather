package cl.com.readarch.apiweather.crosscutting.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.WebIdentityTokenCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.ConversionSchemas;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverterFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * DynamoDB configuration class.@Configuration.
 *
 * @author readarch
 */
@Configuration
public class DynamoDbConfig {

  @Value("${amazon.dynamodb.endpoint}")
  private String amazonDynamoDbEndpoint;

  @Value("${application.isCloud}")
  private boolean isCloud;

  @Value("${application.profileName}")
  private String profileName;

  @Value("${application.tablePrefix}")
  private String tablePrefix;

  @Value("${amazon.dynamodb.region}")
  private String region;

  @Value("${application.credentialsByRole}")
  private boolean credentialsByRole;

  /** This method get the Amazon DynamoDb instance according the environment. */
  @Bean("amazonDynamoDB")
  public AmazonDynamoDB amazonDynamoDb() {
    if (isCloud) {
      return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAwsCredentials()).build();
    }

    return AmazonDynamoDBClientBuilder.standard()
        .withEndpointConfiguration(
            new AwsClientBuilder.EndpointConfiguration(amazonDynamoDbEndpoint, region))
        .build();
  }

  /**
   * Method amazonAwsCredentials.
   *
   * @author readarch
   */
  @Bean("amazonAWSCredentials")
  public AWSCredentialsProvider amazonAwsCredentials() {
    if (credentialsByRole) {
      return WebIdentityTokenCredentialsProvider.builder().build();
    }
    return DefaultAWSCredentialsProviderChain.getInstance();
  }

  @Bean
  @ConditionalOnProperty(prefix = "application", name = "isCloud", havingValue = "true")
  public DynamoDBMapperConfig.TableNameOverride tableNameOverride() {
    return DynamoDBMapperConfig.TableNameOverride.withTableNamePrefix(
        String.format("%s-%s-", profileName, tablePrefix));
  }

  /** This method get the DynamoDb Mapper Config according the parameters. */
  @Bean("dynamoDBMapperConfig")
  @ConditionalOnProperty(prefix = "application", name = "isCloud", havingValue = "true")
  public DynamoDBMapperConfig dynamoDbMapperConfig() {
    return DynamoDBMapperConfig.builder()
        .withTableNameOverride(tableNameOverride())
        .withTypeConverterFactory(DynamoDBTypeConverterFactory.standard())
        .withConversionSchema(ConversionSchemas.V2_COMPATIBLE)
        .build();
  }

  @Bean("dynamoDBMapper")
  @ConditionalOnProperty(prefix = "application", name = "isCloud", havingValue = "true")
  @Primary
  public DynamoDBMapper dynamoDbMapper() {
    return new DynamoDBMapper(amazonDynamoDb(), dynamoDbMapperConfig());
  }
}
