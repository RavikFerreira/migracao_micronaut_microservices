package com.shopping.core.service;

import com.shopping.config.exceptions.ProductResourceNotFoundException;
import com.shopping.core.kafka.Producer;
import com.shopping.core.kafka.ProducerProduct;
import com.shopping.core.models.*;
import com.shopping.core.repository.ProductRepository;
import com.shopping.core.repository.ShoppingRepository;
import com.shopping.core.utils.JsonUtil;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class ProductService {

    @Inject
    private ProductRepository productRepository;
    @Inject
    private ShoppingRepository shoppingRepository;
    @Inject
    private ProducerProduct producerProduct;
    @Inject
    private JsonUtil jsonUtil;
    @Inject
    private EventService eventService;

    public List<Product> productList(){
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        Optional<Product> orders = productRepository.findByIdProduct(product.getIdProduct());

        if (orders.isPresent()) {
            Product existingProduct = orders.get();
            existingProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());
            productRepository.update(existingProduct);
            producerProduct.sendEvent(jsonUtil.toJson(product));
            return existingProduct;
        }

        if(orders.isEmpty()){
            product.setIdProduct(product.getIdProduct());
            product.setQuantity(product.getQuantity());
            productRepository.save(product);
            producerProduct.sendEvent(jsonUtil.toJson(createPayload(product)));
        }

        return product;
    }

    private EventProduct createPayload(Product product) {
        EventProduct event = new EventProduct();
        event.setId(product.getId());
        event.setPayload(product);
        eventService.saveProduct(event);
        return event;

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
