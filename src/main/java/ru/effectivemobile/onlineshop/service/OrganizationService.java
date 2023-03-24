package ru.effectivemobile.onlineshop.service;

import ru.effectivemobile.onlineshop.entity.Company;
import ru.effectivemobile.onlineshop.entity.Product;
import ru.effectivemobile.onlineshop.entity.User;

import java.util.List;

public interface OrganizationService {

    void registerCompany(Company company, User user);

    void addProduct(Product product, Company company);

    List<Product> getAvailableProducts();

    void freezeCompany(Company company);

}
