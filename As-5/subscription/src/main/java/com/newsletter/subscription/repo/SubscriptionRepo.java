package com.newsletter.subscription.repo;

import com.newsletter.subscription.models.Subscription;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Subscription repo.
 */
@Repository
public interface SubscriptionRepo extends ElasticsearchRepository<Subscription, String> {

  /**
   * Search list.
   *
   * @param query the query
   * @return the list
   */
  @Query("{\"multi_match\": {\"query\": \".*?0.*\", \"fields\": [\"name\"], \"fuzziness\" : \"AUTO\", \"prefix_length\" : 2}}")
  List<Subscription> search (String query);

}
