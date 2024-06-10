package com.example.airlinesBuy.Forms;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class OrderForm {

    private String fromStationName;

    private String toStationName;
}
