package com.example.redis.repositories;

import com.example.redis.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


/**
 * The interface Item repository.
 */
public interface ItemRepository extends CrudRepository<Item, Integer> {
}