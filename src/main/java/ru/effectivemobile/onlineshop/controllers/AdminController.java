package ru.effectivemobile.onlineshop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.effectivemobile.onlineshop.dto.CompanyDto;
import ru.effectivemobile.onlineshop.dto.ProductDto;
import ru.effectivemobile.onlineshop.dto.UserDto;
import ru.effectivemobile.onlineshop.entity.Company;
import ru.effectivemobile.onlineshop.entity.Discount;
import ru.effectivemobile.onlineshop.entity.Product;
import ru.effectivemobile.onlineshop.entity.User;
import ru.effectivemobile.onlineshop.service.Impl.AdminServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")

public class AdminController {

    private final AdminServiceImpl adminService;

    @PostMapping("/products")
    public void addProduct(@RequestBody Product product) {
        adminService.addProduct(product);
    }

    @PutMapping("/products/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        adminService.updateProduct(id, updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        adminService.deleteProduct(id);
    }

    @PostMapping("/users/{username}/balance")
    public void addToUserBalance(@PathVariable String username,
                                 @RequestParam double balance) {
        adminService.addToUserBalance(username, balance);
    }

    @PostMapping("/discounts/{id}")
    public void addDiscount(@PathVariable String id,
                            @RequestBody Discount discount) {
        adminService.addDiscount(id, discount);
    }

    @PutMapping("/discounts/{id}")
    public void updateDiscount(@PathVariable String id,
                               @RequestBody Discount updatedDiscount) {
        adminService.updateDiscount(id, updatedDiscount);
    }

    @DeleteMapping("/discounts/{id}")
    public void deleteDiscount(@PathVariable String id) {
        adminService.deleteDiscount(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return adminService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable String id,
                           @RequestBody UserDto updatedUser) {
        adminService.updateUser(id, updatedUser);
    }

    @PostMapping("/users/{username}/notifications")
    public void sendNotificationToUser(@PathVariable String username,
                                       @RequestParam String message) {
        adminService.sendNotificationToUser(username, message);
    }

    @PostMapping("/companies/{id}/registration")
    public void acceptOrganizationRegistration(@PathVariable Long id,
                                               @RequestBody Company company) {
        adminService.acceptOrganizationRegistration(company);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
    }

    @PostMapping("/companies/{name}/freeze")
    public void freezeCompany(@PathVariable String name) {
        adminService.freezeCompany(name);
    }

    @PostMapping("/companies/{name}/activate")
    public void activateCompany(@PathVariable String name) {
        adminService.activateCompany(name);
    }

    @DeleteMapping("/companies/{id}")
    public void deleteCompany(@PathVariable Long id) {
        adminService.deleteCompany(id);
    }

    @GetMapping("/companies")
    public List<Company> getAllCompanies() {
        return adminService.getAllCompanies();
    }
}