package ru.effectivemobile.onlineshop.service.Impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.effectivemobile.onlineshop.dto.UserDto;
import ru.effectivemobile.onlineshop.entity.*;
import ru.effectivemobile.onlineshop.repository.*;
import ru.effectivemobile.onlineshop.service.AdminService;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
@Transactional
public class AdminServiceImpl extends UserServiceImpl implements AdminService {

    private final DiscountRepository discountRepository;

    public AdminServiceImpl(ProductRepository productRepository, PurchaseRepository purchaseRepository, CompanyRepository companyRepository, NotificationRepository notificationRepository, UserRepository userRepository, PasswordEncoder encoder, RoleRepository roleRepository, Properties discountRepository) {
        super(productRepository, purchaseRepository, companyRepository, notificationRepository, userRepository, encoder, roleRepository);
        this.discountRepository = (DiscountRepository) discountRepository;
    }

    @Override
    public void updateProduct(Long id, Product updatedProduct) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setProductName(updatedProduct.getProductName());
            product.setProductDescription(updatedProduct.getProductDescription());
            productRepository.save(product);
        } else {
            throw new IllegalArgumentException("Product not found with ID " + id);
        }
    }

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteAllById(id);
    }

    @Override
    public void addToUserBalance(String username, double balance) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            double currentBalance = user.getBalance();
            user.setBalance(currentBalance + balance);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found with username " + username);
        }
    }

    @Override
    public void addDiscount(String discountId, Discount discount) {
        discountRepository.save(discount);
    }

    @Override
    public void updateDiscount(String discountId, Discount updatedDiscount) {
        Optional<Discount> discountOptional = discountRepository.findById(discountId);
        if (discountOptional.isPresent()) {
            Discount discount = discountOptional.get();
            discount.setDiscountVolume(updatedDiscount.getDiscountVolume());
            discount.setDurationInDays(updatedDiscount.getDurationInDays());
            discountRepository.save(discount);
        } else {
            throw new IllegalArgumentException("Discount not found with ID " + discountId);
        }
    }

    @Override
    public void deleteDiscount(String discountId) {
        discountRepository.deleteById(discountId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    @Override
    public void updateUser(String userId, UserDto updatedUser) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(encoder.encode((updatedUser.getPassword())));
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found with ID " + userId);
        }
    }

    @Override
    public void sendNotificationToUser(String username, String message) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Notification notification = new Notification(message);
//            user.getNotifications(user.setNotifications(notification));
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found with username " + username);
        }
    }

    @Override
    public void acceptOrganizationRegistration(Company company) {
        company.setActivated(true);
        companyRepository.save(company);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void freezeCompany(String companyName) {
        Optional<Company> companyOptional = companyRepository.findByName(companyName);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.setFrozen(true);
            companyRepository.save(company);
        } else {
            throw new IllegalArgumentException("Company not found with name " + companyName);
        }
    }

    @Override
    public void activateCompany(String companyName) {
        Optional<Company> companyOptional = companyRepository.findByName(companyName);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.setActivated(true);
            companyRepository.save(company);
        } else {
            throw new IllegalArgumentException("Company not found with name " + companyName);
        }
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> getCompanyByName(String companyName) {
        return companyRepository.findByName(companyName);
    }

}
