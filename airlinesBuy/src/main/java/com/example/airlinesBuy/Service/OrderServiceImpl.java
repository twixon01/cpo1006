package com.example.airlinesBuy.Service;

import com.example.airlinesBuy.Entity.Order;
import com.example.airlinesBuy.Entity.OrderStatus;
import com.example.airlinesBuy.Entity.Station;
import com.example.airlinesBuy.Entity.User;
import com.example.airlinesBuy.Exception.FailedToFetchUserException;
import com.example.airlinesBuy.Exception.OrderNotFoundException;
import com.example.airlinesBuy.Forms.OrderForm;
import com.example.airlinesBuy.Repository.OrderRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final StationService stationService;
    private final RestTemplate restTemplate;
    private final String baseUrl;

    @Autowired
    public OrderServiceImpl(
        OrderRepository orderRepository,
        StationService stationService,
        RestTemplate restTemplate,
        @Value("${baseUrl}") String baseUrl
    ) {
        this.orderRepository = orderRepository;
        this.stationService = stationService;
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @Override
    @NotNull
    public Order createOrder(@NotNull OrderForm orderForm, @NotNull String userToken) {
        Station fromStation = stationService.getStationByName(orderForm.getFromStationName());
        Station toStation = stationService.getStationByName(orderForm.getToStationName());

        User user = fetchUserFromToken(userToken);

        Order order = new Order();
        order.setStatus(OrderStatus.Check);
        order.setToStation(toStation);
        order.setFromStation(fromStation);
        order.setUserId(Math.toIntExact(Objects.requireNonNull(user.getId())));

        return orderRepository.save(order);
    }

    private User fetchUserFromToken(String userToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", userToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<User> response = restTemplate.exchange(
            baseUrl + "/auth/user", HttpMethod.GET, entity, User.class);

        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            throw new FailedToFetchUserException("Failed to fetch user from token");
        }

        return response.getBody();
    }

    @Override
    public List<Order> getAllOrdersWhereStatusEqCheck() {
        return orderRepository.findAllByStatusEqCheck();
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOneById(Integer id) {
        return orderRepository.findFirstById(id)
            .orElseThrow(OrderNotFoundException::new);
    }
}
