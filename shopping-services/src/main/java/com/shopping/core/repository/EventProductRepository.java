package com.shopping.core.repository;

import com.shopping.core.models.EventProduct;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;

@MongoRepository(databaseName = "shopping-db")
public interface EventProductRepository extends CrudRepository<EventProduct, String> {
}
