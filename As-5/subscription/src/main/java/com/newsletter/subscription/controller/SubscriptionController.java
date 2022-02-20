package com.newsletter.subscription.controller;

import com.newsletter.subscription.dto.SubscriptionDto;
import com.newsletter.subscription.mapper.SubscriptionMapper;
import com.newsletter.subscription.services.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

  @Autowired
  private final SubscriptionService subscriptionService;
  private final SubscriptionMapper subscriptionMapper;

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

  @GetMapping("/{id}")
  ResponseEntity<SubscriptionDto> getById(@PathVariable String id) {
    return ResponseEntity.ok(
        subscriptionMapper.subscriptionToDto(
            subscriptionService.getById(id)
        )
    );
  }

  @GetMapping("/search")
  ResponseEntity<List<SubscriptionDto>> search (@RequestParam String query) {
    return ResponseEntity.ok(subscriptionService.search(query).stream().map(
        subscriptionMapper::subscriptionToDto
    ).collect(Collectors.toList()));
  }

  @PutMapping("/{id}")
  ResponseEntity<SubscriptionDto> update(@RequestBody SubscriptionDto subscriptionDto, @PathVariable String id) {
    return ResponseEntity.ok(
        subscriptionMapper.subscriptionToDto(
            subscriptionService.update(
                subscriptionMapper.dtoToSubscription(subscriptionDto), id
            )
        )
    );
  }

  @DeleteMapping("/{id}")
  void delete(@PathVariable String id) {
    subscriptionService.delete(id);
  }

}
