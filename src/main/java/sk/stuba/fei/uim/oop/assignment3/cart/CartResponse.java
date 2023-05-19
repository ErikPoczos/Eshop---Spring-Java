package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.shoppinglist.ShoppingItem;
import sk.stuba.fei.uim.oop.assignment3.shoppinglist.ShoppingResponse;

import java.util.ArrayList;
import java.util.List;
@Getter
public class CartResponse {
    private final Long id;
    private final List<ShoppingResponse> shoppingList;
    private final boolean payed;

    public CartResponse(Cart cart) {
        shoppingList = new ArrayList<>();
        this.id = cart.getId();
        for(ShoppingItem item : cart.getShoppingList()){
            shoppingList.add(new ShoppingResponse(item.getProductId(), item.getAmount()));
        }
        this.payed = cart.isPayed();
    }
}
