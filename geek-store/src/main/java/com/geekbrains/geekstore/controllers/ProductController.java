package com.geekbrains.geekstore.controllers;


import com.geekbrains.geekstore.dto.ProductDto;
import com.geekbrains.geekstore.exceptions.ResourceNotFoundException;
import com.geekbrains.geekstore.model.Category;
import com.geekbrains.geekstore.model.Product;
import com.geekbrains.geekstore.services.CategoryService;
import com.geekbrains.geekstore.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;



    @GetMapping("/products")
    public Page<ProductDto> findByAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        return productService.findByAll(pageIndex - 1, 8).map(ProductDto::new);
    }


    @GetMapping("/products/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(productService.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Product not found, id: " + id)));
    }


    @DeleteMapping("/products/delete/{id}")
    public void deleteFindById(@PathVariable Long id) {
        productService.deleteFindById(id);
    }



    @PostMapping("/products")
    public ProductDto save(@RequestBody ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setCost(productDto.getCost());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(()
                -> new ResourceNotFoundException("Category not found, title: " + productDto.getCategoryTitle()));
        product.setCategory(category);
        productService.updateOfSave(product);
        return new ProductDto(product);
    }

    @PutMapping("/products")
    public void update(@RequestBody ProductDto productDto) {
        productService.updateProduct(productDto);

    }
}



