package ru.effectivemobile.onlineshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "t_user")
@Accessors(chain = true)
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50, unique = true, nullable = false)
    private String username;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    private String password;

    @Column(name = "role")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "t_user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles = new HashSet<>();

    @Column(name = "balance")
//    TODO BigDecimal
    private double balance;

    @Column(name = "orders")
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    @Column(name = "company")
    @OneToMany
    private List<Company> company;

    @Column(name = "notifications")
    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;

}