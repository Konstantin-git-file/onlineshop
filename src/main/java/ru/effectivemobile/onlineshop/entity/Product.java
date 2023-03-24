package ru.effectivemobile.onlineshop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Entity
@Data
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Table(name = "t_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String productName;

    @Column(name = "description")
    private String productDescription;

    @Column(name = "company")
    @ManyToOne
    private Company company;

    @Column(name = "instock")
    private int instock;

    @Column(name = "price")
    //    TODO BigDecimal
    private double productPrice;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "product_discount")
    @OneToOne
    private Discount productDiscount;

    @Column(name = "reviews")
    @OneToMany
    private List<Review> reviews;

    @Column(name = "keywords")
    @OneToMany
    private List<Keyword> keyword;

    @Column(name = "characteristics")
    private String characteristics;

    @Column(name = "rating")
    private double rating;

}
