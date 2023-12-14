package com.techelevator;

import com.techelevator.items.CandyStoreItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ShoppingCart {

    private Map<String, ShoppingCartItem> cart = new TreeMap<>();

    public List<ShoppingCartItem> getCartList () {
        List<ShoppingCartItem> cartList = new ArrayList<>();
        for (Map.Entry<String, ShoppingCartItem> cartItem : cart.entrySet()) {
            cartList.add(cartItem.getValue());
        }
        return cartList;
    }

    public void addItemToCart(ShoppingCartItem candyToAdd)  {
        if (cart.containsKey(candyToAdd.getProductID())) {
            ShoppingCartItem candyToUpdate = cart.get(candyToAdd.getProductID());
            candyToUpdate.addToQty(candyToAdd.getQty());
            cart.put(candyToAdd.getProductID(), candyToUpdate);
        }
        else {
            cart.put(candyToAdd.getProductID(), candyToAdd);
        }
    }
    public void EmptyCart (){
        cart.clear();

    }

    // FOR JUNIT
    public ShoppingCartItem getCartItem(String productID){
        return cart.get(productID);
    }

}
