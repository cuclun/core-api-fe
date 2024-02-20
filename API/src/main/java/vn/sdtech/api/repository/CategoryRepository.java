package vn.sdtech.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.sdtech.api.dto.response.CategoryResponse;
import vn.sdtech.api.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);

    Optional<Category> findById(Long id);
}
