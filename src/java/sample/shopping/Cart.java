/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ACER
 */
public class Cart {

    private Map<String, ProductDTO> cart;

    public Cart(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public Cart() {
    }

    public Map<String, ProductDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public boolean add(ProductDTO clothes) {
        boolean result = false;

        try {
            if (this.cart == null) {
                this.cart = new HashMap<>();

            }

            if (this.cart.containsKey(clothes.getId())) {
                int currentQuantity = this.cart.get(clothes.getId()).getQuantity();
                clothes.setQuantity(currentQuantity + clothes.getQuantity());
            }

            this.cart.put(clothes.getId(), clothes);
            result = true;
        } catch (Exception e) {
        }

        return result;
    }

    public boolean remove(String id) {
        boolean result = false;

        try {
            if (this.cart != null) {
                if (this.cart.containsKey(id)) {
                    this.cart.remove(id);
                    result = true;
                }
            }
        } catch (Exception e) {
        }

        return result;
    }

    public boolean edit(String id, int quantity) {
        boolean result = false;

        try {
            if (this.cart != null) {
                if (this.cart.containsKey(id)) {

                    ProductDTO clothes = this.cart.get(id);
                    clothes.setQuantity(quantity);
                    this.cart.replace(id, clothes);
                    result = true;
                }
            }
        } catch (Exception e) {
        }

        return result;
    }

}
