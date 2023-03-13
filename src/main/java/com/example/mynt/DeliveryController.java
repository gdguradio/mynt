package com.example.mynt;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calculate")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping("/delivery_cost")
    public double calculateDeliveryCost(@RequestBody Delivery delivery) {
        return deliveryService.calculateCost(delivery);
    }
}