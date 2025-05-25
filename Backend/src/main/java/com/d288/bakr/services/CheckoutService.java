package com.d288.bakr.services;

import com.d288.bakr.dto.Purchase;
import com.d288.bakr.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);

}

