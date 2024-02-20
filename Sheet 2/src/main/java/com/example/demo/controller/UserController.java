package com.example.demo.controller;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/index")
    public String user(Model model,
                       @RequestParam(name = "category", defaultValue = "") Long id) {
        model.addAttribute("categories", categoryRepository.findAll());
        List<Product> productList = null;
        if(id == null) {
            productList = productRepository.findByDeletedIsFalse();
        } else {
            productList = productRepository.findAllByCategory_Id(id);
        }
        model.addAttribute("products", productList);
        return "User";
    }

    @GetMapping("/search")
    public String search(Model model,
                       @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        model.addAttribute("categories", categoryRepository.findAll());
        List<Product> productList = null;
        if(keyword.isBlank()) {
            productList = productRepository.findAll();
        } else {
            productList = productRepository.findAllByNameContaining(keyword);
        }
        model.addAttribute("products", productList);
        return "User";
    }

    @GetMapping("/payment/{id}")
    public String order(Model model, @PathVariable("id") Long id) {
        Bill bill = new Bill();
        model.addAttribute("product", productRepository.findById(id).get());
        model.addAttribute("bill", bill);
        return "Payment";
    }

    @PostMapping("/payment/{id}")
    public String payment(@ModelAttribute("bill") Bill bill, @PathVariable("id") Long id, Model model) {
        Product product = productRepository.findById(id).get();
        bill.setProduct(product);
        if (product.getQuantity() == 0) {
            model.addAttribute("message", "Sản phẩm nớ hết rồiii");
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("products", productRepository.findAll());
            return "User";
        }
        if(bill.getQuantity() < 1) {
            model.addAttribute("message", "So luong phai lon hon 1");
            model.addAttribute("order", product);
            return "redirect:/payment/{id}";
        }
        if(bill.getQuantity() > product.getQuantity()) {
            model.addAttribute("message", "Trong kho chỉ còn " + product.getQuantity() + " sản phẩm thui");
            model.addAttribute("order", product);
            return "redirect:/payment/{id}";
        }
        orderRepository.save(bill);
        product.setQuantity(product.getQuantity() - bill.getQuantity());
        productRepository.save(product);
        return "redirect:/index";
    }
}
