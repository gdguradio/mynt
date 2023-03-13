package com.example.mynt;

import lombok.*;

@Data
public class Delivery {

    private final double MAX_WEIGHT = 50;

    private double weight;
    private double height;
    private double width;
    private double length;
    private double maxWeight = MAX_WEIGHT;
    private String voucherCode;

}
