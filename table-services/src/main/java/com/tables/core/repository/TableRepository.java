package com.tables.core.repository;

import com.tables.core.models.TableBar;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@MongoRepository(databaseName = "table-db")
public interface TableRepository extends CrudRepository<TableBar, String> {

    Optional<TableBar> findByIdTable(String idTable);
}
