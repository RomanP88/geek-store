package com.geekbrains.geekstore.controllers;

import com.geekbrains.geekstore.dto.ProductDto;
import com.geekbrains.geekstore.services.CartService;
import com.geekbrains.geekstore.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Component
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;


    @GetMapping("/carts")
    public List<ProductDto> findByAll() {
        return cartService.cartAllOfProduct().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @GetMapping("/carts/delete/{id}")
    public void deleteFindById(@PathVariable Long id) {
        cartService.deleteCart(id);
    }


    @PostMapping("/carts/{id}")
    public void addFindById(@PathVariable Long id) {
        cartService.addCart(id);

    }
}





