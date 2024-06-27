package com.payment.models;

//import com.orders.dtos.OrderDTO;

//import com.menu.models.Menu;
import io.micronaut.data.annotation.NamingStrategy;
import io.micronaut.data.model.naming.NamingStrategies;
import io.micronaut.serde.annotation.Serdeable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tb_payment")
@Serdeable
//@SerdeImport(OrderDTO.class)
@NamingStrategy(NamingStrategies.LowerCase.class)
public class Payment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    private String idOrder;

//    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "order_menu",
//            joinColumns = @JoinColumn(name = "id_order"),
//            inverseJoinColumns = @JoinColumn(name = "id_menu")
//    )
//    private List<Menu> menu;

    public Payment(Long id, String idOrder) {
        this.id = id;
        this.idOrder = idOrder;
    }
    public Payment() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

//    public List<Menu> getMenu() {
//        return menu;
//    }
//    public void setMenu( List<Menu> menu) {
//        this.menu = menu;
//    }
}
