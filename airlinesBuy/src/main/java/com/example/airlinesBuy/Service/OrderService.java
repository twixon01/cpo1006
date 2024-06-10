package com.example.airlinesBuy.Service;

import com.example.airlinesBuy.Entity.Order;
import com.example.airlinesBuy.Forms.OrderForm;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    @NotNull
    Order createOrder(
        @NotNull OrderForm orderForm,
        @NotNull String userToken
    );

    List<Order> getAllOrdersWhereStatusEqCheck();

    Order save(Order order);

    Order getOneById(Integer id);
}
