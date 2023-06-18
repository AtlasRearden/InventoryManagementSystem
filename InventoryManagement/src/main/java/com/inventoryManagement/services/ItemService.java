package com.inventoryManagement.services;

import com.inventoryManagement.entities.Item;

import java.util.List;

public interface ItemService {
    Item addItem(Item item);
    Item updateItem(Long itemId, Item updatedItem);
    void deleteItem(Long itemId);
    List<Item> getAllItems();
    Item getItemById(Long itemId);

}
