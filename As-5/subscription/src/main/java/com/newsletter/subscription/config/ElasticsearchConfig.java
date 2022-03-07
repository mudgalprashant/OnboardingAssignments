package com.newsletter.subscription.config;

import com.newsletter.subscription.constants.Constant;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * The type Elasticsearch config.
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = Constant.ES_REPO_BASE_PACKAGE)
@ComponentScan(basePackages = { Constant.ES_COMPONENT_BASE_PACKAGE })
public class ElasticsearchConfig {

  /**
   * Client rest high level client.
   *
   * @return the rest high level client
   */
  @Bean
  public RestHighLevelClient client() {
    ClientConfiguration clientConfiguration
        = ClientConfiguration.builder()
        .connectedTo("localhost:9200")
        .build();

    return RestClients.create(clientConfiguration).rest();
  }

  /**
   * Elasticsearch template elasticsearch operations.
   *
   * @return the elasticsearch operations
   */
  @Bean
  public ElasticsearchOperations elasticsearchTemplate() {
    return new ElasticsearchRestTemplate(client());
  }
}