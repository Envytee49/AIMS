package com.example.aims.entity.cart;

import java.util.ArrayList;
import java.util.List;


public class Cart {
	private static Cart cart;
	private static List<CartMedia> listCartMedia = new ArrayList<CartMedia>();

	public static Cart getCart() {
		if(cart == null) {
			return new Cart();
		}
		return cart;
	}
	public List<CartMedia> getListCartMedia() {
		return listCartMedia;
	}
}