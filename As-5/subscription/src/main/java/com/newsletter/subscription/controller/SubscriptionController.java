package com.newsletter.subscription.controller;

import com.newsletter.subscription.constants.Constant;
import com.newsletter.subscription.dto.SubscriptionDto;
import com.newsletter.subscription.mapper.SubscriptionMapper;
import com.newsletter.subscription.services.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Subscription controller.
 */
@AllArgsConstructor
@RestController
@RequestMapping(Constant.SUBSCRIPTIONS_MAPPING)
public class SubscriptionController {

  @Autowired
  private final SubscriptionService subscriptionService;
  private final SubscriptionMapper subscriptionMapper;

  /**
   * Create response entity.
   *
   * @param subscriptionDto the subscription dto
   * @return the response entity
   */
  @PostMapping
  ResponseEntity<SubscriptionDto> create(@RequestBody SubscriptionDto subscriptionDto) {
    return ResponseEntity.ok(
        subscriptionMapper.subscriptionToDto(
            subscriptionService.create(
                subscriptionMapper.dtoToSubscription(subscriptionDto)
            )
        )
    );
  }

  /**
   * Gets all.
   *
   * @return the all
   */
  @GetMapping
  ResponseEntity<List<SubscriptionDto>> getAll() {
    return ResponseEntity.ok(
        subscriptionService
            .getAll()
            .stream()
            .map(subscriptionMapper::subscriptionToDto)
            .collect(Collectors.toList())
    );
  }

  /**
   * Gets by id.
   *
   * @param id the id
   * @return the by id
   */
  @GetMapping(Constant.BY_ID_MAPPING)
  ResponseEntity<SubscriptionDto> getById(@PathVariable String id) {
    return ResponseEntity.ok(
        subscriptionMapper.subscriptionToDto(
            subscriptionService.getById(id)
        )
    );
  }

  /**
   * Search response entity.
   *
   * @param query the query
   * @return the response entity
   */
  @GetMapping(Constant.SEARCH_MAPPING)
  ResponseEntity<List<SubscriptionDto>> search (@RequestParam String query) {
    return ResponseEntity.ok(subscriptionService.search(query).stream().map(
        subscriptionMapper::subscriptionToDto
    ).collect(Collectors.toList()));
  }

  /**
   * Update response entity.
   *
   * @param subscriptionDto the subscription dto
   * @param id              the id
   * @return the response entity
   */
  @PutMapping(Constant.BY_ID_MAPPING)
  ResponseEntity<SubscriptionDto> update(@RequestBody SubscriptionDto subscriptionDto, @PathVariable String id) {
    return ResponseEntity.ok(
        subscriptionMapper.subscriptionToDto(
            subscriptionService.update(
                subscriptionMapper.dtoToSubscription(subscriptionDto), id
            )
        )
    );
  }

  /**
   * Delete.
   *
   * @param id the id
   */
  @DeleteMapping(Constant.BY_ID_MAPPING)
  void delete(@PathVariable String id) {
    subscriptionService.delete(id);
  }

}
