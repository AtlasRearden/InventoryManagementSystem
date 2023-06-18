package com.inventoryManagement.services;

import com.inventoryManagement.entities.Item;
import com.inventoryManagement.repository.ItemRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ItemServiceImpl implements ItemService{

    @Autowired
    private final ItemRepo itemRepo;

    @Override
    public Item addItem(Item item){
        return itemRepo.save(item);
    }

    @Override
    public Item updateItem(Long item_id, Item updatedItem) {
        Item item = getItemById(item_id);
        item.setName(updatedItem.getName());
        item.setPrice(updatedItem.getPrice());
        return itemRepo.save(item);
    }

    @Override
    public void deleteItem(Long item_id){
        itemRepo.deleteById(item_id);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepo.findAll();
    }

    @Override
    public Item getItemById(Long item_id){
        return itemRepo.findById(item_id)
                .orElseThrow(() -> new NoSuchElementException("Item not found with ID: " + item_id));
    }

}
