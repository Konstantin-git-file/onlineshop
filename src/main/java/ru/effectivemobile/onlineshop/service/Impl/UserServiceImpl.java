package ru.effectivemobile.onlineshop.service.Impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.effectivemobile.onlineshop.dto.*;
import ru.effectivemobile.onlineshop.dto.request.SignupRequest;
import ru.effectivemobile.onlineshop.dto.responce.MessageResponse;
import ru.effectivemobile.onlineshop.entity.*;
import ru.effectivemobile.onlineshop.exception.InsufficientStockException;
import ru.effectivemobile.onlineshop.exception.NotAvailableCompanyException;
import ru.effectivemobile.onlineshop.exception.ReturnExpiredException;
import ru.effectivemobile.onlineshop.repository.*;
import ru.effectivemobile.onlineshop.service.UserService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public abstract class UserServiceImpl implements UserService {

    protected final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;
    protected final CompanyRepository companyRepository;
    private final NotificationRepository notificationRepository;
    protected final UserRepository userRepository;
    public final PasswordEncoder encoder;
    private final RoleRepository roleRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> searchProducts(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Product> getProductsByCompany(Company companyName) {
        return productRepository.findByCompany(companyName);
    }

    @Override
    public Purchase buyProduct(Product product, int quantity, UserDto user, CompanyDto companyDto) throws InsufficientStockException, NotAvailableCompanyException {
        if (companyDto.isActivated() && !companyDto.isFrozen()) {
            throw new NotAvailableCompanyException("Company not available");
        }
        if (user.getBalance() >= product.getProductPrice() && product.getInstock() > 0) {
            throw new InsufficientStockException("Not enough stock available or not enough money for purchase");
        }
        user.setBalance((user.getBalance() - product.getProductPrice()));
        product.setInstock(product.getQuantity() - 1);
        Purchase purchase = new Purchase(product, quantity, user);
        purchaseRepository.save(purchase);
        product.setInstock(product.getInstock() - quantity);
        productRepository.save(product);
        return purchase;
    }

    @Override
    public void addProductReview(ProductDto product, String text, int rating, User user) {
        if (getPurchaseHistory(user)
                .stream()
                .anyMatch(purchase -> purchase.getProduct().equals(product))) {
            Review review = new Review(user, text, rating);
            product.getReviews().add(review);
            //TODO
            product.setRating(((product.getRating() * (product.getReviews().size() - 1)) + rating) / product.getReviews().size());
        }
    }

    @Override
    public List<Purchase> getPurchaseHistory(User user) {
        return purchaseRepository.findByUser(user);
    }

    @Override
    public void returnPurchase(Purchase purchase) throws ReturnExpiredException {
        if (purchase.getPurchaseDate().plusDays(1).isAfter(LocalDateTime.now())) {
            throw new ReturnExpiredException("Return period has expired");
        }
        purchase.setReturned(true);
        User user = purchase.getUser();
        Product product = purchase.getProduct();
        user.setBalance(user.getBalance() + product.getProductPrice());
        product.setInstock(product.getQuantity() + 1);
        purchaseRepository.delete(purchase);
        productRepository.save(product);
    }

    @Override
    public void registerCompany(Company companyName, Company companyDescription, Company logo, User user) {
        companyRepository.save(new Company(companyName, companyDescription, logo, user));
    }

    @Override
    public void addProduct(User user, Company company, Product product) throws NotAvailableCompanyException {

        if (!user.getCompany().equals(company)) {
            throw new NotAvailableCompanyException("User does not belong to this company");
        }

        if (!company.isActivated() || !company.isFrozen()) {
            throw new NotAvailableCompanyException("Option is not available");
        }

        Product productNew = new Product();
        product.setProductName(product.getProductName());
        product.setProductDescription(product.getProductDescription());
        product.setCompany(product.getCompany());
        product.setInstock(product.getQuantity() + 1);
        product.setProductPrice(product.getProductPrice());
        product.setProductDiscount(product.getProductDiscount());

        productRepository.addNewProduct(productNew);
    }

    @Override
    public List<Notification> getUserNotifications(User user) {
        return notificationRepository.findByUser(user);
    }

    @Override
    public void replenishBalance(UserDto userDto, double amount) {
        User user = userRepository.findById(userDto.getId()).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userDto.getId()));
        double newBalance = user.getBalance() + amount;
        user.setBalance(newBalance);
        userRepository.save(user);
    }

    @Override
    public MessageResponse registerUser(SignupRequest rq) {
        if (userRepository.existsByUsername(rq.getUsername())) {
            return new MessageResponse("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(rq.getEmail())) {
            return new MessageResponse("Error: Email is already in use!");
        }

        User user = new User()
                .setUsername(rq.getUsername())
                .setEmail(rq.getEmail())
                .setPassword(encoder.encode((rq.getPassword())));

        List<ERole> strRoles = rq.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole =
                    roleRepository
                            .findByName(ERole.USER)
                            .orElseThrow(() -> new RuntimeException("Error: " + strRoles + " is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(
                    role -> {
                        Role roleEntity =
                                roleRepository
                                        .findByName(role)
                                        .orElseThrow(() -> new RuntimeException("Error: " + role + " is not found."));
                        roles.add(roleEntity);
                    });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return new MessageResponse("User registered successfully!");
    }

    public abstract void updateProduct(Long id, Product updatedProduct);
}

