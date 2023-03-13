package com.example.mynt;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    public double calculateCost(Delivery delivery) {
        String ruleName = this.determineRule(delivery);
        double volume = delivery.getHeight() * delivery.getWidth() * delivery.getLength();
        double voucherDiscount = 0;
        try {
            voucherDiscount = VoucherDiscountRequester.requestDiscount(delivery.getVoucherCode());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        double totalCost = 0;
        if(ruleName == "Heavy Parcel"){
            totalCost = delivery.getWeight() * 20 * voucherDiscount;
        }else if(ruleName == "Small Parcel"){
            totalCost = volume * .03 * voucherDiscount;
        }else if(ruleName == "Medium Parcel"){
            totalCost = volume * .04 * voucherDiscount;
        }else if(ruleName == "Large Parcel"){
            totalCost = volume * .05 * voucherDiscount;
        }
        return totalCost;
    }

    public String determineRule(Delivery delivery) {
        double volume = delivery.getHeight() * delivery.getWidth() * delivery.getLength(); // calculate volume

        String ruleName= "Reject";
        if(delivery.getWeight() > 50){
            ruleName= "Reject";
        }else if(delivery.getWeight() <= 50 && delivery.getWeight() > 10){
            ruleName= "Heavy Parcel";
        }else if(volume < 1500){
            ruleName= "Heavy Parcel";
        }else if(volume < 2500 && volume >= 1500){
            ruleName= "Medium Parcel";
        }else if(volume >= 2500){
            ruleName= "Large Parcel";
        }

        return ruleName;
    }
}
