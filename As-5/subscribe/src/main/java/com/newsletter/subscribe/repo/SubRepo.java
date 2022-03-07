package com.newsletter.subscribe.repo;

import com.newsletter.subscribe.models.Sub;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SubRepo extends CrudRepository<Sub, Long> {

  List<Sub> findAllBySubscriberId(Long subscriberId);

  List<Sub> findAllBySubscriptionId(String subscriptionId);

}
