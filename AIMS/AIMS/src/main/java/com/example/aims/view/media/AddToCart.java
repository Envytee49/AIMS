package com.example.aims.view.media;

import com.example.aims.controller.CartController;
import com.example.aims.entity.cart.CartMedia;
import com.example.aims.exception.cart.CartAlreadyInCartException;
import com.example.aims.exception.media.NotEnoughMediaException;
import com.example.aims.utils.Utils;
import javafx.scene.control.Alert;

public interface AddToCart {
    default void addToCart(CartController cartController, CartMedia cartMedia) throws
            CartAlreadyInCartException,
            NotEnoughMediaException {
        try {
            cartController.addMediaToCart(cartMedia);
            Utils.showAlert("Cart","Media added", Alert.AlertType.INFORMATION);
        }catch (CartAlreadyInCartException e) {
            e.printStackTrace();
            Utils.showAlert("Cart","Media already in cart", Alert.AlertType.ERROR);
        }catch (NotEnoughMediaException e) {
            e.printStackTrace();
            Utils.showAlert("Cart","Not enough media", Alert.AlertType.ERROR);
        }
    }
}
