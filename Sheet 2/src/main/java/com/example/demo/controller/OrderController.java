package com.example.demo.controller;

import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/bill")
    public String showOrders(Model model) {
        model.addAttribute("bills", orderRepository.findAll());
        return "Orders";
    }

    @GetMapping("/bill/delete/{id}")
    public String deleteBill(@PathVariable("id") Long id) {
        orderRepository.deleteById(id);
        return "redirect:/bill";
    }
}
