package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    int countProductsByCategory_Id(Long id);

    Page<Product> findAllByDeletedIsFalse(Pageable pageable);

    boolean existsByNameAndIdIsNot(String name, Long id);

    List<Product> findAllByNameContaining(String name);

    List<Product> findByDeletedIsFalse();

    List<Product> findAllByCategory_Id(Long id);
}