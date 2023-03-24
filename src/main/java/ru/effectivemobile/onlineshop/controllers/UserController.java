package ru.effectivemobile.onlineshop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.effectivemobile.onlineshop.dto.*;
import ru.effectivemobile.onlineshop.dto.request.PurchaseRequest;
import ru.effectivemobile.onlineshop.dto.request.SignupRequest;
import ru.effectivemobile.onlineshop.dto.responce.MessageResponse;
import ru.effectivemobile.onlineshop.entity.Product;
import ru.effectivemobile.onlineshop.entity.Purchase;
import ru.effectivemobile.onlineshop.entity.User;
import ru.effectivemobile.onlineshop.exception.InsufficientStockException;
import ru.effectivemobile.onlineshop.exception.NotAvailableCompanyException;
import ru.effectivemobile.onlineshop.exception.ReturnExpiredException;
import ru.effectivemobile.onlineshop.service.Impl.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PreAuthorize("hasRole('USER'or'ADMIN')")
    @PostMapping("/register")
    public MessageResponse registerUser(@RequestBody SignupRequest rq) {
        return userService.registerUser(rq);
    }

    @PreAuthorize("hasRole('USER'or'ADMIN')")
    @GetMapping("/{id}/purchases_history")
    public List<Purchase> getUserPurchasesHistory(@PathVariable User id) {
        return userService.getPurchaseHistory(id);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{id}/buy")
    public Purchase buyProduct(@PathVariable Product product,
                               @RequestBody PurchaseRequest purchaseRequest, CompanyDto companyDto) throws InsufficientStockException, NotAvailableCompanyException {
        return userService.buyProduct(product, purchaseRequest.getQuantity(),purchaseRequest.getUser(), companyDto);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{id}/return")
    public void returnProduct(@PathVariable Purchase purchase) throws ReturnExpiredException {
        userService.returnPurchase(purchase);
    }
    @PreAuthorize("hasRole('USER'or'ADMIN')")
    @PutMapping("/{id}/replenish_balance")
    public void replenishBalance(@PathVariable UserDto userDto, @RequestParam double amount) {
        userService.replenishBalance(userDto, amount);
    }

    @PreAuthorize("hasRole('USER'or'ADMIN')")
    @PostMapping("/{id}/review")
    public void leaveReview(@PathVariable ProductDto productDto,String text, int rating, User user) {
        userService.addProductReview(productDto, text, rating, user);
    }
}

