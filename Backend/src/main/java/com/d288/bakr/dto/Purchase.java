package com.d288.bakr.dto;

import com.d288.bakr.entities.Customer;
import com.d288.bakr.entities.Cart;
import com.d288.bakr.entities.CartItem;

import java.util.Set;

public class Purchase {

    private Customer customer;
    private Cart cart;
    private Set<CartItem> cartItems;

    // No-argument constructor
    public Purchase() {
    }

    // Getters and Setters
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
