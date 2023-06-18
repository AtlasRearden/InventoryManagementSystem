package com.inventoryManagement.services;

import com.inventoryManagement.entities.Admin;
import com.inventoryManagement.entities.Item;
import com.inventoryManagement.entities.User;

import java.util.List;

public interface AdminService {

    Admin createAdmin(Admin admin);
    User createUser(Long adminId, User user);

    void addUserToAdmin(Long adminId, Long userId);

    Item addItem(Item item);

    void deleteUser(Long id);

    Item updateItem(Long itemId, Item updatedItem);

    void deleteItem(Long itemId);

    List<Item> getAllItems();

    Item getItemById(Long itemId);

    List<User> getAllUsers();

    List<User> getUsersInAdmin(Long adminId);
}
