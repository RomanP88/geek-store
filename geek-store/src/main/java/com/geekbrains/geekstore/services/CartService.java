package com.geekbrains.geekstore.services;

import com.geekbrains.geekstore.cart.Cart;
import com.geekbrains.geekstore.exceptions.ResourceNotFoundException;
import com.geekbrains.geekstore.model.Product;
import com.geekbrains.geekstore.repositories.ProductRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
@Data
@RequiredArgsConstructor
public class CartService {

    private final ProductRepository productRepository;
    private final Cart cart;



    public List<Product> cartAllOfProduct(){
        return cart.getCartAllProduct();
    }

    public void addCart(Long id){
        cart.getCartAllProduct().add(productRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Product not found, id: " + id)));
    }

    public void deleteCart(Long id){
        cart.getCartAllProduct().removeIf(product -> product.getId().equals(id));
    }

}
