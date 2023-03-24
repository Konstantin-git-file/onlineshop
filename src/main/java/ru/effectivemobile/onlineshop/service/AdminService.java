package ru.effectivemobile.onlineshop.service;

import ru.effectivemobile.onlineshop.dto.ProductDto;
import ru.effectivemobile.onlineshop.dto.UserDto;
import ru.effectivemobile.onlineshop.entity.Company;
import ru.effectivemobile.onlineshop.entity.Discount;
import ru.effectivemobile.onlineshop.entity.Product;
import ru.effectivemobile.onlineshop.entity.User;

import java.util.List;
import java.util.Optional;

public interface AdminService extends UserService {

    void addProduct(Product product);

    void deleteProduct(Long id);

    void addToUserBalance(String username, double balance);

    void addDiscount(String discountId, Discount discount);

    void updateDiscount(String discountId, Discount updatedDiscount);

    void deleteDiscount(String discountId);

    List<User> getAllUsers();

    User getUserById(Long id);

    void updateUser(String userId, UserDto updatedUser);

    void sendNotificationToUser(String username, String message);

    void acceptOrganizationRegistration(Company company);

    void deleteUser(Long userId);

    void freezeCompany(String companyName);

    void activateCompany(String companyName);

    void deleteCompany(Long id);

    List<Company> getAllCompanies();

    Optional<Company> getCompanyByName(String companyName);

}
