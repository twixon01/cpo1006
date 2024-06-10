package com.example.airlinesBuy.Controllers;

import com.example.airlinesBuy.Entity.Order;
import com.example.airlinesBuy.Forms.OrderForm;
import com.example.airlinesBuy.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity createOrder(
        @RequestBody OrderForm orderForm,
        HttpServletRequest request
    ){
        String userToken = request.getHeader("Authorization");

        Order order = orderService.createOrder(orderForm, userToken);

        return ResponseEntity.ok(order);
    }

    @GetMapping("{id}")
    public ResponseEntity getOrderInfo(
        @PathVariable(required = true) Integer id
    ){
        Order order = orderService.getOneById(id);

        return ResponseEntity.ok(order);
    }
}
