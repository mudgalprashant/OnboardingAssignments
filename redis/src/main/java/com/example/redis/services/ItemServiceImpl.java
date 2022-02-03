package com.example.redis.services;

import com.example.redis.constants.Constant;
import com.example.redis.exceptions.IdNotFoundException;
import com.example.redis.models.Item;
import com.example.redis.repositories.ItemRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Item service.
 */
@Service
class ItemServiceImpl implements ItemService {

  private final Validator validator;
  private final ItemRepository itemRepository;
  private final Log log = LogFactory.getLog(Validator.class);

  /**
   * Instantiates a new Item service.
   *
   * @param itemRepository the item repository
   */
  public ItemServiceImpl(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
    this.validator = new Validator();
  }

  @Override
  public Item createItem(Item item) {
    if (validator.validateItem(item)) {
      itemRepository.save(item);
    }
    return item;
  }

  @Override
  public List<Item> getAllItems() {
    return (List<Item>) itemRepository.findAll();
  }

  @Override
  public Item getItemById(Integer id) throws IdNotFoundException {
    return itemRepository.findById(id).orElseThrow(
        () -> new IdNotFoundException(Constant.INVALID_ENTRY));
  }

  @Override
  public Item updateItem(Item newItem, Integer id) throws NoSuchElementException {
    return itemRepository.findById(id)
        .map(item -> {
          item.setName(newItem.getName());
          item.setCategory(newItem.getCategory());
          item.setPrice(newItem.getPrice());
          item.setQuantity(newItem.getQuantity());
          return itemRepository.save(item);
        })
        .orElseGet(() -> itemRepository.save(newItem));
  }

  @Override
  public void deleteItem(Integer id) throws NoSuchElementException {
    itemRepository.deleteById(id);
  }
}
