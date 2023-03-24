package ru.effectivemobile.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.effectivemobile.onlineshop.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}