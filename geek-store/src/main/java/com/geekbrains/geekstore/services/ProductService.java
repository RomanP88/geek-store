package com.geekbrains.geekstore.services;


import com.geekbrains.geekstore.dto.ProductDto;
import com.geekbrains.geekstore.exceptions.ResourceNotFoundException;
import com.geekbrains.geekstore.model.Category;
import com.geekbrains.geekstore.model.Product;
import com.geekbrains.geekstore.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;


  public Page<Product> findByAll(int pageIndex, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }


    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteFindById(Long id) {
        productRepository.deleteById(id);
    }

    public void updateOfSave(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(ProductDto productDto) {
        Product product = findById(productDto.getId()).orElseThrow(()
                -> new ResourceNotFoundException("Category not found, id: " + productDto.getId()));
        product.setTitle(productDto.getTitle());
        product.setCost(productDto.getCost());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(()
                -> new ResourceNotFoundException("Category not found, title: " + productDto.getCategoryTitle()));
        product.setCategory(category);

    }


}



