package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    public boolean deleteById(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category == null) {
            return false;
        }
        if(productRepository.countProductsByCategory_Id(id) > 0) {
            return false;
        }
        try {
            categoryRepository.delete(category);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
