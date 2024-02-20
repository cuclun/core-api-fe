package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.impl.ProductServiceImpl;
import com.example.demo.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/product")
    public String product(Model model,
                          @RequestParam(name = "page", defaultValue = "1") int page) {

        Pageable pageable = PageRequest.of(page - 1, 5, Sort.by("id").descending());
        Page<Product> products = productRepository.findAllByDeletedIsFalse(pageable);

        List<Product> productList = new ArrayList<>(products.getContent());

        model.addAttribute("currentPage", products.getPageable().getPageNumber());
        model.addAttribute("totalPage", products.getTotalPages());
        model.addAttribute("products", productList);
        return "Product";
    }

    @GetMapping("/addProduct")
    public String addProductPage(Model model) {
        Product productForm = new Product();
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("productForm", productForm);
        return "AddProduct";
    }

    @GetMapping("/product/update/{id}")
    public String updateProductPage(Model model, @ModelAttribute("productForm") Product productForm, @PathVariable("id") Long id) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("productForm", productRepository.findById(id).get());
        return "AddProduct";
    }

    @PostMapping({"/addProduct", "/product/update/{id}"})
    public String addCategory(Model model, @ModelAttribute("productForm") Product productForm,
                              @RequestParam("file") MultipartFile file) {

        if (productForm.getId() != null) {
            Product updateProduct = productRepository.findById(productForm.getId()).orElse(null);
            if (updateProduct != null) {
                productForm.setId(updateProduct.getId());
                productForm.setImage(UploadUtils.save(file));
            }
        } else {
            productForm.setImage(UploadUtils.save(file));
        }

        if(productService.saveProduct(productForm) == null) {
            model.addAttribute("productForm", productForm);
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("message", "Tên sản phẩm đã có");
            return "AddProduct";
        }
        return "redirect:/product";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
        if(!productService.deleteProduct(id)) {
            model.addAttribute("message", "Khong the xoa san pham!");
            model.addAttribute("products", productRepository.findAll());
            return "Product";
        }
        return "redirect:/product";
    }
}
