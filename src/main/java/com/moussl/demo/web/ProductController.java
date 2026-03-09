package com.moussl.demo.web;

import com.moussl.demo.entities.Product;
import com.moussl.demo.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("index")
    public String index(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("productslist", products);
        return "products";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/index";
    }

    @GetMapping("/newProduct")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "new-product";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@Valid Product product, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            return "redirect:/newProduct";
        }
        productRepository.save(product);
        return "redirect:/index ";
    }
}
