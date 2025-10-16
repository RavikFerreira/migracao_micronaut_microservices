package com.tables.core.service;

import com.tables.config.exceptions.ItIsNotPossibleToAddAProductToTheMenuWithTheSameId;
import com.tables.config.exceptions.ProductResourceNotFoundException;
import com.tables.core.kafka.Producer;
import com.tables.core.models.EventProduct;
import com.tables.core.models.Product;
import com.tables.core.repository.ProductRepository;
import com.tables.core.repository.TableRepository;
import com.tables.core.utils.JsonUtil;
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
    @Inject
    private Producer producer;
    @Inject
    private JsonUtil jsonUtil;
    @Inject
    private EventService eventService;

    public List<Product> productList(){
        return productRepository.findAll();
    }

    public Product addProduct(Product product) throws ItIsNotPossibleToAddAProductToTheMenuWithTheSameId {
        Optional<Product> orders = productRepository.findByIdProduct(product.getIdProduct());
        if(orders.isPresent()){
            throw new ItIsNotPossibleToAddAProductToTheMenuWithTheSameId(product.getIdProduct());
        }
        product.setIdProduct(product.getIdProduct());
        product.setQuantity(product.getQuantity());
        productRepository.save(product);
        producer.sendEventProduct(jsonUtil.toJson(createProductPayload(product)));
        return product;
    }

    private EventProduct createProductPayload(Product product){
        EventProduct event = new EventProduct();
        event.setId(product.getId());
        event.setPayload(product);
        eventService.save(event);
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
