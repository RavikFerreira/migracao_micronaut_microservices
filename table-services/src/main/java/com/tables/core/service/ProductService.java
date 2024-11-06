package com.tables.core.service;

import com.tables.config.exceptions.ItIsNotPossibleToAddAProductToTheMenuWithTheSameId;
import com.tables.config.exceptions.OrderResourceNotFoundException;
import com.tables.core.models.Product;
import com.tables.core.repository.ProductRepository;
import com.tables.core.repository.TableRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class ProductService {

    @Inject
    private ProductRepository productRepository;
    @Inject
    private TableRepository tableRepository;

    public List<Product> productList(){
        return productRepository.findAll();
    }

    public Product addProduct(Product product) throws ItIsNotPossibleToAddAProductToTheMenuWithTheSameId {
        Optional<Product> orders = productRepository.findByIdProduct(product.getIdProduct());
        if(orders.isPresent()){
            throw new ItIsNotPossibleToAddAProductToTheMenuWithTheSameId(product.getIdProduct());
        }
        product.setIdProduct(product.getIdProduct());
        product.setQuantity(1);
        productRepository.save(product);
        return product;
    }
    public Product searchProduct(String idProduct){
        Product product = productRepository.findByIdProduct(idProduct).orElseThrow(() -> new OrderResourceNotFoundException(idProduct));
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
        Product product = productRepository.findByIdProduct(idProduct).orElseThrow(() -> new OrderResourceNotFoundException(idProduct));
        productRepository.delete(product);
        return product;
    }
}
