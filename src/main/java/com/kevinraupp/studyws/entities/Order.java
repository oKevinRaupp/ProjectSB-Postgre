package com.kevinraupp.studyws.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kevinraupp.studyws.entities.enums.OrderStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
    private Instant instant;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;
    private Integer orderStatus;
    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> itens = new HashSet<>();

    public Order() {
    }

    public Order(Instant instant, User client,OrderStatus orderStatus) {
        this.instant = instant;
        this.client = client;
        setOrderStatus(orderStatus);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if(orderStatus != null){
            this.orderStatus = orderStatus.getCode();
        }
    }
    public Set<OrderItem> getItens() {
        return itens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
