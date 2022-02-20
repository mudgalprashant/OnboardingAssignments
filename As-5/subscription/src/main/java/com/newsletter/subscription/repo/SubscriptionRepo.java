package com.newsletter.subscription.repo;

import com.newsletter.subscription.models.Subscription;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepo extends ElasticsearchRepository<Subscription, String> {

  @Query("{\"multi_match\": {\"query\": \".*?0.*\", \"fields\": [\"name\"], \"fuzziness\" : \"AUTO\", \"prefix_length\" : 2}}")
  List<Subscription> search (String query);

}
