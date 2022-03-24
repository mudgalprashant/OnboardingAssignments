package com.newsletter.subscription.repo;

import com.newsletter.subscription.constants.QueryConstant;
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
  @Query(QueryConstant.SEARCH_BY_NAME_QUERY)
  List<Subscription> searchByName (String query);

  @Query(QueryConstant.SEARCH_BY_CATEGORY_QUERY)
  List<Subscription> searchByCategory(String query);

  @Query(QueryConstant.SEARCH_BY_PUBLISHER_ID_QUERY)
  List<Subscription> searchByPublisherId(Long query);

}
