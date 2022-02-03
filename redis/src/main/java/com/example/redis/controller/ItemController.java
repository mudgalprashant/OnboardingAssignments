package com.example.redis.controller;

import com.example.redis.exceptions.IdNotFoundException;
import com.example.redis.mapper.ItemMapper;
import com.example.redis.models.Item;
import com.example.redis.models.ItemDto;
import com.example.redis.services.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Item controller.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemController {

  @Autowired
  private final ItemService itemService;
  private final ItemMapper itemMapper;

  /**
   * Create response entity.
   *
   * @param itemDto the item dto
   * @return the response entity
   */
  @PostMapping
  @CachePut(value = "items")
  ResponseEntity<ItemDto> create(@RequestBody ItemDto itemDto) {
    Item item = itemMapper.toItem(itemDto);
    item = itemService.createItem(item);
    return ResponseEntity.ok(
        itemMapper.toItemDto(item)
    );
  }

  /**
   * Gets all.
   *
   * @return the all
   */
  @GetMapping
  @Cacheable(value = "items")
  ResponseEntity<List<ItemDto>> getAll() {
    List<ItemDto> itemDtoList = itemService
        .getAllItems()
        .stream()
        .map(itemMapper::toItemDto)
        .collect(Collectors.toList());
    return ResponseEntity.ok((itemDtoList));
  }

  /**
   * Gets by id.
   *
   * @param id the id
   * @return the by id
   * @throws IdNotFoundException the id not found exception
   */
  @GetMapping("/{id}")
  @Cacheable(value = "items", key = "#id")
  ResponseEntity<ItemDto> getById(@PathVariable Integer id) throws IdNotFoundException {
    Item item = itemService.getItemById(id);
    return ResponseEntity.ok(
        itemMapper.toItemDto(item)
    );
  }

  /**
   * Update response entity.
   *
   * @param itemDto the item dto
   * @param id      the id
   * @return the response entity
   */
  @PutMapping("/{id}")
  @Cacheable(value = "items", key = "#id")
  ResponseEntity<ItemDto> update(@RequestBody ItemDto itemDto, @PathVariable Integer id) {
    Item item = itemMapper.toItem(itemDto);
    item = itemService.updateItem(item, id);
    return ResponseEntity.ok(
        itemMapper.toItemDto(item)
    );
  }

  /**
   * Delete.
   *
   * @param id the id
   */
  @DeleteMapping("/{id}")
  @CacheEvict(value = "items", key = "#id")
  void delete(@PathVariable Integer id) {
    itemService.deleteItem(id);
  }

}
