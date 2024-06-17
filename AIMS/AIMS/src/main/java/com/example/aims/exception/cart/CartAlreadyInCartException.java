package com.example.aims.exception.cart;

import com.example.aims.exception.RuntimeException;

public class CartAlreadyInCartException extends RuntimeException {
    public CartAlreadyInCartException() {
        super("ERROR: Cart is already in the cart");
    }
}
