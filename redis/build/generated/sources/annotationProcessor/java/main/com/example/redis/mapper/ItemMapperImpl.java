package com.example.redis.mapper;

import com.example.redis.models.Item;
import com.example.redis.models.ItemDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

/**
 * The type Item mapper.
 */
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-03T15:20:06+0530",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.3.3.jar, environment: Java 11.0.11 (Oracle Corporation)"
)
@Component
public class ItemMapperImpl implements ItemMapper {

    @Override
    public ItemDto toItemDto(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemDto itemDto = new ItemDto();

        itemDto.setId( item.getId() );
        itemDto.setName( item.getName() );
        itemDto.setCategory( item.getCategory() );
        itemDto.setPrice( item.getPrice() );
        itemDto.setQuantity( item.getQuantity() );

        return itemDto;
    }

    @Override
    public Item toItem(ItemDto itemDto) {
        if ( itemDto == null ) {
            return null;
        }

        Item item = new Item();

        item.setId( itemDto.getId() );
        item.setName( itemDto.getName() );
        item.setCategory( itemDto.getCategory() );
        item.setPrice( itemDto.getPrice() );
        item.setQuantity( itemDto.getQuantity() );

        return item;
    }
}
