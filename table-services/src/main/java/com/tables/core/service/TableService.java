package com.tables.core.service;


import com.tables.config.exceptions.CannotCreateATableWithTheSameId;
import com.tables.config.exceptions.CannotDeleteABusyTable;
import com.tables.config.exceptions.OrderResourceNotFoundException;
import com.tables.config.exceptions.TablesResourceNotFoundException;
import com.tables.core.kafka.Producer;
import com.tables.core.models.Event;
import com.tables.core.models.Order;
import com.tables.core.models.Product;
import com.tables.core.models.TableBar;
import com.tables.core.models.enums.State;
import com.tables.core.repository.ProductRepository;
import com.tables.core.repository.TableRepository;
import com.tables.core.utils.JsonUtil;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class TableService {

    private static final String TRANSACTION_ID_PATTERN = "%s %s";

    @Inject
    private TableRepository tableRepository;
    @Inject
    private ProductRepository productRepository;
    @Inject
    private Producer producer;
    @Inject
    private JsonUtil jsonUtil;
    @Inject
    private EventService eventService;

    public List<TableBar> list(){
        List<TableBar> tableBars = tableRepository.findAll();

        for(TableBar tableBar : tableBars){
            double account = 0.0;
            if(tableBar.getOrder() != null){
                List<Product> products = tableBar.getOrder().getProducts();
                if(products != null) {
                    for(Product product : products) {
                        account += product.getPrice() * product.getQuantity();
                    }
                    tableBar.setAccount(account);
                }
            }
        }
        return tableBars;
    }

    public TableBar addTables(TableBar tables){
        Optional<TableBar> tableExists = tableRepository.findByIdTable(tables.getIdTable());
        if(tableExists.isPresent()) {
            throw new CannotCreateATableWithTheSameId("Cannot create a table with the same id: " + tables.getIdTable());
        }
        tables.setOrder(tables.getOrder());
        tables.setState(State.LIVRE);

        tableRepository.save(tables);

        return tables;
    }
    public TableBar addOrder(String idTable){
        TableBar tables = tableRepository.findByIdTable(idTable).orElseThrow(() -> new CannotCreateATableWithTheSameId("Cannot create a table with the same id: " + idTable));

        Order order = new Order();
        tables.setOrder(order);
        tables.setState(State.OCUPADO);
        order.setIdOrder(tables.getIdTable());

        tableRepository.update(tables);
        return tables;
    }
    public TableBar addProductInOrder(String idTable, String idProduct) {
        TableBar tables = tableRepository.findByIdTable(idTable).orElseThrow(() -> new TablesResourceNotFoundException(idTable));
        Product productExists = productRepository.findByIdProduct(idProduct).orElseThrow(() -> new OrderResourceNotFoundException(idProduct));

        boolean orderNotExists = false;
        List<Product> products = tables.getOrder().getProducts();
        if(products == null){
            products = new ArrayList<>();
            tables.getOrder().setProducts(products);
            }
        for(Product product : products){
            if(product.getIdProduct().equals(productExists.getIdProduct())){
                product.setQuantity(product.getQuantity() + 1);
                orderNotExists = true;
                break;
            }
        }
        if (!orderNotExists) {
            productExists.setQuantity(1);
            products.add(productExists);
        }
        tableRepository.update(tables);
        producer.sendEvent(jsonUtil.toJson(createPayload(tables)));
        return tables;
    }

    private Event createPayload(TableBar tables){
        Event event = new Event();
        event.setTableId(tables.getIdTable());
        event.setTransactionId(String.format(TRANSACTION_ID_PATTERN, Instant.now().getEpochSecond(), UUID.randomUUID()));
        event.setPayload(tables);
        event.setCreatedAt(LocalDateTime.now());
        eventService.save(event);
        return event;
    }

    public TableBar search(String table) {
        TableBar tables = tableRepository.findByIdTable(table).orElseThrow(() -> new TablesResourceNotFoundException("Tables resource not found!"));
        if(tables.getOrder() != null) {
            List<Product> products = tables.getOrder().getProducts();
            double account = 0.0;
            for (Product product : products) {
                account += product.getPrice() * product.getQuantity();
            }
            tables.setAccount(account);
        }

        return tables;
    }

    public TableBar delete(String idTable) throws CannotDeleteABusyTable {
        TableBar tables = tableRepository.findByIdTable(idTable).orElseThrow(()-> new TablesResourceNotFoundException("Tables resource not found!"));
        if(tables.getState() == State.OCUPADO){
          throw new CannotDeleteABusyTable("Cannot delete a busy table!");
        } if(tables != null){
            tableRepository.delete(tables);
        }
        return tables;
    }

}
