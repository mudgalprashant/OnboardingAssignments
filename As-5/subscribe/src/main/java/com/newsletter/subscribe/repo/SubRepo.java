package com.newsletter.subscribe.repo;

import com.newsletter.subscribe.models.Sub;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The interface Sub repo.
 */
@Repository
public interface SubRepo extends CrudRepository<Sub, Long> {

  /**
   * Find all by subscriber id list.
   *
   * @param subscriberId the subscriber id
   * @return the list
   */
  List<Sub> findAllBySubscriberId(Long subscriberId);

  /**
   * Find all by subscription id list.
   *
   * @param subscriptionId the subscription id
   * @return the list
   */
  List<Sub> findAllBySubscriptionId(String subscriptionId);

}
