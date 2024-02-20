package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/category")
    public String category(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "Category";
    }

    @GetMapping("/index/category/{id}")
    public String findByCategory(@PathVariable("id") Long id, Model model) {
        model.addAttribute("products", productRepository.findAllByCategory_Id(id));
        return "redirect:/index/category/{id}";
    }

    @GetMapping("/addCategory")
        public String addCategoryPage(Model model) {
            Category categoryForm = new Category();
        model.addAttribute("categoryForm", categoryForm);
        return "AddCategory";
    }

    @GetMapping("/category/update/{id}")
    public String updateCategoryPage(Model model, @PathVariable Long id) {
        model.addAttribute("categoryForm", categoryRepository.findById(id).get());
        return "AddCategory";
    }

    @PostMapping({"/addCategory", "/category/update/{id}"})
    public String addCategory(@ModelAttribute("categoryForm") Category categoryForm) {
        if (categoryForm.getId() != null) {
            Category updateCategory = categoryRepository.findById(categoryForm.getId()).orElse(null);
            if (updateCategory != null) {
                updateCategory.setName(categoryForm.getName());
                categoryRepository.save(updateCategory);
            }
        } else {
            categoryRepository.save(categoryForm);
        }
        return "redirect:/category";
    }

    @GetMapping("/category/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        if(!categoryService.deleteById(id)) {
            model.addAttribute("message", "Danh muc co ton tai san pham");
            model.addAttribute("categories", categoryRepository.findAll());
            return "Category";
        }
        return "redirect:/category";
    }
}
