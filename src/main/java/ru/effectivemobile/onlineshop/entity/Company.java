package ru.effectivemobile.onlineshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    private String companyDescription;

    private String companyLogo;

    @OneToMany(mappedBy = "company")
    private List<Product> products;

    private double commission;

    private boolean isActivated;

    private boolean isFrozen;

    public Company(Company companyName, Company companyDescription, Company logo, User user) {
    }


}
