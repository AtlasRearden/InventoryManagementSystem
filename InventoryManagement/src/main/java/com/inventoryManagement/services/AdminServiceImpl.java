package com.inventoryManagement.services;

//import com.inventoryManagement.entities.Item;
//import com.inventoryManagement.entities.User;
//import com.inventoryManagement.repository.AdminRepo;
//import com.inventoryManagement.repository.UserRepo;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@AllArgsConstructor
//@NoArgsConstructor(force = true)
//public class AdminServiceImpl implements AdminService{
//
//    @Autowired
//    private final ItemService itemService;
//
//    @Autowired
//    AdminRepo adminRepo;
//
//    @Autowired
//    UserRepo userRepo;
//
//    @Autowired
//    private final UserService userService;
//
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public Admin createAdmin(Admin admin) {
//        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
//        return adminRepo.save(admin);
//    }
//
////    @Override
////    public User createUser(Long adminId, User user) {
////        Admin admin = adminRepo.findById(adminId).orElse(null);
////
////        if (admin != null) {
////            user.setAdmin(admin);
////            user.setPassword(passwordEncoder.encode(user.getPassword()));
////            return userRepo.save(user);
////        }
////
////        return null;
////    }
//
//    public String addAdmin(Admin admin){
//        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
//        adminRepo.save(admin);
//        return "admin added";
//    }
//
//    public String addUser(User user){
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepo.save(user);
//        return "user added";
//    }
//
//    @Override
//    public Item addItem(Item item) {
//        return itemService.addItem(item);
//    }
//
//    @Override
//    public void deleteUser(Long id){
//        userService.deleteUser(id);
//    }
//
//    @Override
//    public Item updateItem(Long itemId, Item updatedItem) {
//        return itemService.updateItem(itemId, updatedItem);
//    }
//
//    @Override
//    public void deleteItem(Long itemId) {
//        itemService.deleteItem(itemId);
//    }
//
//    @Override
//    public List<Item> getAllItems() {
//        return itemService.getAllItems();
//    }
//
//    @Override
//    public Item getItemById(Long itemId) {
//        return itemService.getItemById(itemId);
//    }
//
//
//    public void addUserToAdmin(Long adminId, Long userId) {
//        Admin admin= adminRepo.findById(adminId).orElse(null);
//        User user = userRepo.findById(userId).orElse(null);
//
//        if (admin != null && user != null) {
//            userRepo.save(user);
//            admin.getUsers().add(user);
//            adminRepo.save(admin);
//        }
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//
//    @Override
//    public List<User> getUsersInAdmin(Long adminId) {
//        Admin admin = adminRepo.findById(adminId)
//                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + adminId));
//        return admin.getUsers();
//    }
//}
