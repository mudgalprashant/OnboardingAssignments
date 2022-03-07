package com.newsletter.subscription.services;

import com.newsletter.subscription.models.Subscription;
import com.newsletter.subscription.repo.SubscriptionRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class SubscriptionServiceImpl implements SubscriptionService{

  @Autowired
  private final SubscriptionRepo subscriptionRepo;

  @Override
  public Subscription create(Subscription subscription) {
    return subscriptionRepo.save(subscription);
  }

  @Override
  public Subscription getById(String id) {
    return subscriptionRepo.findById(id).orElseThrow();
  }

  @Override
  public List<Subscription> getAll() {
    return  subscriptionRepo.findAll(PageRequest.of(0, 10000)).toList();
  }

  @Override
  public List<Subscription> search(String query) {
    return subscriptionRepo.search(query);
  }

  @Override
  public Subscription update(Subscription newSubscription, String id) {
    Subscription subscription = subscriptionRepo.findById(id).orElseThrow();
    copyNonNull(newSubscription, subscription);
    subscription.setId(id);
    return subscriptionRepo.save(subscription);
  }

  @Override
  public void delete(String id) {
    subscriptionRepo.deleteById(id);
  }
  @Override
  public void deleteAll() {
    subscriptionRepo.deleteAll();
  }

  private void copyNonNull(Object source, Object destination){
    BeanUtils.copyProperties(source, destination,
        getNullPropertyNames(source));
  }

  /**
   * Returns an array of null properties of an object.
   */
  private String[] getNullPropertyNames (Object source) {

    final BeanWrapper src = new BeanWrapperImpl(source);
    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

    Set<String> emptyNames = new HashSet<>();

    for(java.beans.PropertyDescriptor pd : pds) {
      //check if value of this property is null then add it to the collection
      Object srcValue = src.getPropertyValue(pd.getName());
      if (srcValue == null) emptyNames.add(pd.getName());
    }

    String[] result = new String[emptyNames.size()];
    return emptyNames.toArray(result);
  }
}
