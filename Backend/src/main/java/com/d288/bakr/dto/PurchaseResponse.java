package com.d288.bakr.dto;

public class PurchaseResponse {

    private String orderTrackingNumber;

    // No-argument constructor
    public PurchaseResponse() {
    }

    // Constructor with orderTrackingNumber
    public PurchaseResponse(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }

    // Getter and Setter
    public String getOrderTrackingNumber() {
        return orderTrackingNumber;
    }

    public void setOrderTrackingNumber(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }
}
