package com.example.aims.controller;

import com.example.aims.entity.cart.Cart;
import com.example.aims.entity.cart.CartMedia;
import com.example.aims.exception.cart.CartAlreadyInCartException;
import com.example.aims.exception.media.NotEnoughMediaException;

import java.util.List;

public class CartController {
    private static Cart cart;
    private static List<CartMedia> listCartMedia;

    public CartController() {
        cart = Cart.getCart();
        listCartMedia = Cart.getCart().getListCartMedia();
    }

    public Cart getCart() {
        return cart;
    }
    public List<CartMedia> getListCartMedia() {
        return listCartMedia;
    }
    public List<CartMedia> requestToPlaceOder() {
        List<CartMedia> cartMediaList = cart.getListCartMedia()
                .stream()
                .filter(CartMedia::isChecked)
                .toList();
        if (!cartMediaList.isEmpty()) return cartMediaList;

        return null;
    }

    public int getAmount() {
        int sum = listCartMedia.stream()
                .filter(CartMedia::isChecked)
                .mapToInt(cartMedia -> cartMedia.getQuantity() * cartMedia.getPrice())
                .sum();

        return sum;
    }


    public boolean checkAvailabilityOfSpecificProduct(CartMedia med) {
        return med.getQuantity() >= med.getMedia().getQuantity();
    }

    public int getMediaIndex(CartMedia cartMedia) {
        for (int i = 0; i < listCartMedia.size(); i++) {
            if (listCartMedia.get(i).getMedia().getId() == cartMedia.getMedia().getId()) {
                return i;
            }
        }
        return -1;
    }

    public void addMediaToCart(CartMedia media) throws CartAlreadyInCartException, NotEnoughMediaException {
        if (getMediaIndex(media) != -1) {
            throw new CartAlreadyInCartException();
        }

        if (checkAvailabilityOfSpecificProduct(media)) {
            throw new NotEnoughMediaException("Not enough media");
        }

        listCartMedia.add(media);

    }

    public void removeMediaFromCart(CartMedia media) {
        listCartMedia.remove(media);
    }

    public void updateQuantityOfMedia(CartMedia media, int quantity) {
        listCartMedia.get(getMediaIndex(media)).setQuantity(quantity);
    }

    public void updateMediaCheck(CartMedia media) {
        CartMedia cartMedia = listCartMedia.get(getMediaIndex(media));
        listCartMedia.get(getMediaIndex(media)).setChecked(!cartMedia.isChecked());
    }
}
