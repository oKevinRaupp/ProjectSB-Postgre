package com.kevinraupp.studyws.resources;

import com.kevinraupp.studyws.entities.Order;
import com.kevinraupp.studyws.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/orders")
public class OrderResource {
    @Autowired
    private OrderService orderService;
    @GetMapping
    public ResponseEntity<List<Order>> findAll(){
        List<Order> list = orderService.finall();
        return ResponseEntity.ok(list);
    }
    @GetMapping(value = "/{id}")
    public Optional<Order> findById(@PathVariable Long id){
        return orderService.findById(id);
    }

}
