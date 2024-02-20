package vn.sdtech.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.sdtech.api.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    int countProductsByCategory_Id(Long id);

    List<Product> findAllByNameContaining(String name);

    List<Product> findAllByCategory_Id(Long id);

    boolean existsByName(String name);

    boolean existsByNameAndIdIsNot(String name, Long id);

    Page<Product> findAllByDeletedIsFalse(Pageable pageable);
}