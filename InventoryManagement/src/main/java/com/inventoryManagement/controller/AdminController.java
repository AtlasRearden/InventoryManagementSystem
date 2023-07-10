package com.inventoryManagement.controller;

//import com.inventoryManagement.entities.Admin;
//import com.inventoryManagement.entities.Item;
//import com.inventoryManagement.entities.User;
//import com.inventoryManagement.services.AdminServiceImpl;
//import com.inventoryManagement.services.UserServiceImpl;
//import lombok.AllArgsConstructor;
//import org.apache.logging.log4j.util.PerformanceSensitive;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping(value="/main")
//@ResponseBody
//@AllArgsConstructor
//public class AdminController {
//
//
//    @Autowired
//    private final AdminServiceImpl  adminService;
//
//   @Autowired
//   private final UserServiceImpl userService;
//
//    @GetMapping("/display")
//    public List<Item> getAllItems(){
//        return adminService.getAllItems();
//    }
//
//    @GetMapping("/display/{id}")
//    public Optional<Item> getItemById(@PathVariable Long id){
//        return Optional.ofNullable(adminService.getItemById(id));
//    }
//
//    @GetMapping("/displayU")
//    public List<User> getAllUsers(){
//        return adminService.getAllUsers();
//    }
//
//    @PostMapping("/new")
//    public String addNewUser(@RequestBody User user){
//        return adminService.addUser(user);
//    }
//
//    @PostMapping("/ad")
//    public String addNewAdmin(@RequestBody Admin admin){
//        return adminService.addAdmin(admin);
//    }
//
//    @PostMapping
//    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
//        Admin createdAdmin= adminService.createAdmin(admin);
//        return ResponseEntity.ok(createdAdmin);
//    }
//
////    @PostMapping("/{admin_id}/usr")
////    public User createUser(@PathVariable Long admin_id, @RequestBody User user){
////        return adminService.createUser(admin_id, user);
////    }
//
//    @PostMapping("/items")
//    public Item addItem(@RequestBody Item item) {
//        return adminService.addItem(item);
//    }
//
//    @PutMapping("/items/{id}")
//    public Optional <Item> updateItem(@PathVariable Long id, @RequestBody Item item) {
//        return Optional.ofNullable(adminService.updateItem(id, item));
//    }
//
//    @DeleteMapping("/remove/{id}")
//    public void deleteItem(@PathVariable Long id){
//        adminService.deleteItem(id);
//    }
//
//    @DeleteMapping("/removeUser/{id}")
//        public void deleteUser(@PathVariable Long id) {
//            adminService.deleteUser(id);
//
//    }
//
//    @PostMapping("/{adminId}/users")
//    public ResponseEntity<User> addUserToAdmin(@PathVariable Long adminId, @RequestBody Long userId){
//        adminService.addUserToAdmin(adminId,userId);
//        return ResponseEntity.ok().build();
//    }
//
//}
