package com.shopping.core.service;


import com.shopping.config.exceptions.ProductResourceNotFoundException;
import com.shopping.core.models.Product;
import com.shopping.core.repository.ProductRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@Singleton
@AllArgsConstructor
public class ProductService {

    @Inject
    private ProductRepository productRepository;

    public List<Product> productList(){
        return productRepository.findAll();
    }

    public Product addProduct(Product product){
        Optional<Product> orders = productRepository.findByIdProduct(product.getIdProduct());

        if (orders.isPresent()) {
            Product existingProduct = orders.get();
            existingProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());
            productRepository.update(existingProduct);
        }

        if(orders.isEmpty()){
            product.setIdProduct(product.getIdProduct());
            product.setQuantity(product.getQuantity());
            productRepository.save(product);
        }
        return product;
    }

    public Product searchProduct(String idProduct){
        Product product = productRepository.findByIdProduct(idProduct).orElseThrow(() -> new ProductResourceNotFoundException(idProduct));
        return product;
    }

    public Product updateOrderInProduct(String idProduct, Product product) {
        searchProduct(idProduct);
        product.setIdProduct(product.getIdProduct());
        product.setName(product.getName());
        product.setPrice(product.getPrice());
        productRepository.save(product);
        return product;
    }

    public Product deleteProduct(String idProduct) {
        Product product = productRepository.findByIdProduct(idProduct).orElseThrow(() -> new ProductResourceNotFoundException(idProduct));
        productRepository.delete(product);
        return product;
    }

}
