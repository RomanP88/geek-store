package com.geekbrains.geekstore.cart;

import com.geekbrains.geekstore.model.Product;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@Data
@RequiredArgsConstructor
public class Cart {





   private List<Product> cartAllProduct = new ArrayList<>();



}
