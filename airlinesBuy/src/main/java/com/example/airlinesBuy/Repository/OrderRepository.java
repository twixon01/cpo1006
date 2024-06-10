package com.example.airlinesBuy.Repository;

import com.example.airlinesBuy.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select * from order_table as ot where ot.status = 'Check'", nativeQuery = true)
    List<Order> findAllByStatusEqCheck();

    Optional<Order> findFirstById(@Param(value = "id") Integer id);
}
