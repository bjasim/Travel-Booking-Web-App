package com.d288.bakr.services;

import com.d288.bakr.dao.CartRepository;
import com.d288.bakr.dao.CustomerRepository;
import com.d288.bakr.dto.Purchase;
import com.d288.bakr.dto.PurchaseResponse;
import com.d288.bakr.entities.Cart;
import com.d288.bakr.entities.CartItem;
import com.d288.bakr.entities.Customer;
import com.d288.bakr.entities.Excursion;
import com.d288.bakr.entities.StatusType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutServiceImpl.class);

    private CustomerRepository customerRepository;
    private CartRepository cartRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        try {
            logger.info("Starting order placement...");

            // Validate and log purchase object
            if (purchase == null) {
                throw new IllegalArgumentException("Purchase object cannot be null");
            }

            logger.info("Purchase object validation:");
            logger.info("- Customer: {}", purchase.getCustomer());
            logger.info("- Cart: {}", purchase.getCart());
            logger.info("- Cart Items: {}", purchase.getCartItems());

            // Validate customer
            if (purchase.getCustomer() == null || purchase.getCustomer().getId() == null) {
                throw new IllegalArgumentException("Customer and Customer ID cannot be null");
            }

            // Get customer and validate
            Customer customer;
            try {
                customer = customerRepository.findById(purchase.getCustomer().getId())
                        .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + purchase.getCustomer().getId()));
                logger.info("Found customer: ID={}, Name={} {}",
                        customer.getId(), customer.getFirstName(), customer.getLastName());
            } catch (Exception e) {
                logger.error("Error retrieving customer: ", e);
                throw new RuntimeException("Error retrieving customer: " + e.getMessage());
            }

            // Create new Cart
            Cart cart = new Cart();

            // Generate tracking number
            String orderTrackingNumber = generateOrderTrackingNumber();
            logger.info("Generated tracking number: {}", orderTrackingNumber);

            // Set up the cart
            cart.setOrderTrackingNumber(orderTrackingNumber);
            cart.setStatus(StatusType.ordered);
            cart.setCustomer(customer);

            // Initialize total package price
            BigDecimal totalPackagePrice = BigDecimal.ZERO;

            // Process cart items
            Set<CartItem> cartItems = purchase.getCartItems();
            if (cartItems != null && !cartItems.isEmpty()) {
                logger.info("Processing {} cart items", cartItems.size());

                for (CartItem item : cartItems) {
                    if (item == null) {
                        logger.warn("Null cart item found in purchase");
                        continue;
                    }

                    logger.info("Processing cart item: {}", item);
                    logger.info("- Vacation: {}", item.getVacation());
                    logger.info("- Excursions: {}", item.getExcursions());

                    if (item.getVacation() == null) {
                        throw new IllegalArgumentException("CartItem must have a vacation");
                    }

                    // Add vacation price to total
                    if (item.getVacation().getTravel_price() != null) {
                        totalPackagePrice = totalPackagePrice.add(item.getVacation().getTravel_price());
                        logger.info("Added vacation price: {}", item.getVacation().getTravel_price());
                    }

                    // Reset ID to ensure new item creation
                    item.setId(null);

                    // Set up bidirectional relationships
                    item.setCart(cart);
                    cart.add(item);

                    // Handle excursions
                    if (item.getExcursions() != null && !item.getExcursions().isEmpty()) {
                        logger.info("Processing {} excursions for cart item", item.getExcursions().size());
                        Set<Excursion> excursions = new HashSet<>();

                        for (Excursion excursion : item.getExcursions()) {
                            if (excursion != null) {
                                // Add excursion price to total
                                if (excursion.getExcursion_price() != null) {
                                    totalPackagePrice = totalPackagePrice.add(excursion.getExcursion_price());
                                    logger.info("Added excursion price: {}", excursion.getExcursion_price());
                                }

                                // Only add the excursion reference, don't try to persist it
                                excursions.add(excursion);
                                logger.info("Added excursion reference: {}", excursion.getId());
                            }
                        }

                        // Set the excursions on the cart item
                        item.setExcursions(excursions);
                    } else {
                        logger.info("No excursions to process for cart item");
                        item.setExcursions(new HashSet<>());
                    }
                }
            } else {
                logger.warn("No cart items found in the purchase");
            }

            // Set the total package price on the cart
            cart.setPackage_price(totalPackagePrice);
            logger.info("Set total package price: {}", totalPackagePrice);

            // Initialize customer's carts if needed
            if (customer.getCarts() == null) {
                customer.setCarts(new HashSet<>());
                logger.info("Initialized customer's carts collection");
            }

            // Add cart to customer's carts
            customer.getCarts().add(cart);

            try {
                // Save the customer first to establish the relationship
                logger.info("Saving customer with cart...");
                logger.info("Customer before save: {}", customer);
                logger.info("Cart before save: {}", cart);

                // Save the customer which will cascade to cart and cart items
                customer = customerRepository.save(customer);
                logger.info("Customer after save: {}", customer);

                // Get the saved cart from the customer's carts
                Cart savedCart = customer.getCarts().stream()
                        .filter(c -> c.getOrderTrackingNumber().equals(orderTrackingNumber))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Failed to retrieve saved cart"));

                logger.info("Successfully saved cart with ID: {}", savedCart.getId());
                logger.info("Saved cart details: {}", savedCart);

            } catch (Exception e) {
                logger.error("Error saving cart: ", e);
                throw new RuntimeException("Failed to save cart: " + e.getMessage());
            }

            logger.info("Order successfully placed with tracking number: {}", orderTrackingNumber);
            return new PurchaseResponse(orderTrackingNumber);

        } catch (Exception e) {
            logger.error("Error placing order: ", e);
            throw new RuntimeException("Error processing order: " + e.getMessage(), e);
        }
    }

    private String generateOrderTrackingNumber() {
        // Generate a random UUID
        return UUID.randomUUID().toString();
    }
}
