package sk.stuba.fei.uim.oop.assignment3.cart;

import java.util.List;

public interface ICartService {
    List<Cart> getAll();
    Cart getAllById(Long id);
    Cart addProductToCart(long productId, long cardId, int amount);
    Cart createCart();
    void deleteCartById(Long id);
}
