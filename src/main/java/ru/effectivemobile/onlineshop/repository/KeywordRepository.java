package ru.effectivemobile.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.effectivemobile.onlineshop.entity.Keyword;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {
}