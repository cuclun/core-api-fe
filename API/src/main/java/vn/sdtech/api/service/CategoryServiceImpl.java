package vn.sdtech.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.sdtech.api.dto.request.CategoryRequest;
import vn.sdtech.api.dto.response.CategoryResponse;
import vn.sdtech.api.mapper.CategoryMapper;
import vn.sdtech.api.model.Category;
import vn.sdtech.api.repository.CategoryRepository;
import vn.sdtech.api.repository.ProductRepository;

@Service
public class CategoryServiceImpl {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    public boolean isExists(String name) {
        return categoryRepository.existsByName(name);
    }

    public Page<Category> findAll(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);

        return categories;
    }

    public CategoryResponse findById(Long id) {
        return categoryMapper.categoryToCategoryResponse(categoryRepository.findById(id).orElse(null));
    }

    public Category save(CategoryRequest categoryRequest) {
        Category category = categoryMapper.categoryRequestDTOToCategory(categoryRequest);

        return categoryRepository.save(category);
    }

    public CategoryResponse update(Long categoryId, CategoryRequest categoryRequest) {
        Category existingCategory = categoryRepository.findById(categoryId).get();

        existingCategory.setName(categoryRequest.getName());

        return CategoryMapper.INSTANCE.categoryToCategoryResponse(categoryRepository.save(existingCategory));
    }

    public void delete(Long categoryId) {
        if (productRepository.countProductsByCategory_Id(categoryId) > 0) {
            throw new RuntimeException("Còn sản phẩm trong danh mục");
        }
        categoryRepository.deleteById(categoryId);
    }
}
