package ru.effectivemobile.onlineshop.service;

import ru.effectivemobile.onlineshop.dto.*;
import ru.effectivemobile.onlineshop.dto.request.SignupRequest;
import ru.effectivemobile.onlineshop.dto.responce.MessageResponse;
import ru.effectivemobile.onlineshop.entity.*;
import ru.effectivemobile.onlineshop.exception.InsufficientStockException;
import ru.effectivemobile.onlineshop.exception.NotAvailableCompanyException;
import ru.effectivemobile.onlineshop.exception.ReturnExpiredException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    List<Product> getProductsByCompany(Company company);

    List<Product> searchProducts(String name);

    Purchase buyProduct(Product product, int quantity, UserDto user, CompanyDto companyDto) throws InsufficientStockException, NotAvailableCompanyException;

    void addProductReview(ProductDto product, String review, int rating, User user);

    List<Purchase> getPurchaseHistory(User user);

    void returnPurchase(Purchase purchase) throws ReturnExpiredException;

    void registerCompany(Company companyName, Company companyDescription, Company logo, User user);

    void addProduct(User user, Company company, Product product) throws NotAvailableCompanyException;

    List<Notification> getUserNotifications(User user);

    void replenishBalance(UserDto userDto, double amount);

    MessageResponse registerUser(SignupRequest rq);
}
