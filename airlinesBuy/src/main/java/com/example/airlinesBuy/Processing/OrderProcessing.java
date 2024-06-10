package com.example.airlinesBuy.Processing;

import com.example.airlinesBuy.Entity.Order;
import com.example.airlinesBuy.Entity.OrderStatus;
import com.example.airlinesBuy.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@EnableScheduling
public class OrderProcessing {

    private final OrderService orderService;

    @Autowired
    public OrderProcessing(
        OrderService orderService
    ) {
        this.orderService = orderService;
    }

    @Transactional
    @Scheduled(timeUnit = TimeUnit.SECONDS, fixedDelay = 10L)
    public void processOrder(){
        OrderStatus[] statuses = OrderStatus.values();
        Random random = new Random();

        List<Order> orderList =  orderService.getAllOrdersWhereStatusEqCheck();

       for (Order order: orderList) {
           int randomIndex = random.nextInt(2) + 1;
           OrderStatus randomStatus = statuses[randomIndex];
           order.setStatus(randomStatus);
           orderService.save(order);
       }
    }


}
