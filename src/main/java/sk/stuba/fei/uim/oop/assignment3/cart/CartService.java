package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.product.IProductService;
import sk.stuba.fei.uim.oop.assignment3.product.Product;
import sk.stuba.fei.uim.oop.assignment3.shoppinglist.ShoppingItem;
import sk.stuba.fei.uim.oop.assignment3.shoppinglist.ShoppingRepository;

import java.util.List;

@Service
public class CartService implements ICartService{
    private final CartRepository repository;

    @Autowired
    private ShoppingRepository shoppingRepository;

    @Autowired
    private IProductService productService;

    @Autowired
    public CartService(CartRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Cart> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Cart createCart() {
        Cart newCart = new Cart();
        newCart.getShoppingList().clear();
        newCart.setPayed(false);
        return this.repository.save(newCart);
    }

    @Override
    public Cart getAllById(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public  void deleteCartById(Long id) {
        this.repository.findById(id).orElseThrow();
        this.repository.deleteById(id);
    }

    public Cart addProductToCart(long cartId, long productId, int amount){
        Cart cart = this.repository.findById(cartId).orElseThrow();
        Product product = this.productService.getById(productId);

        if (cart.isPayed() || product.getAmount() < amount) {
            throw new BadRequestException();
        }

        this.productService.decreaseAmount(productId, amount);
        ShoppingItem shoppingItem = null;
        for (ShoppingItem item : cart.getShoppingList()) {
            if(item.getProductId() == productId) {
                shoppingItem = item;
            }
        }

        if(shoppingItem == null) {
            shoppingItem = new ShoppingItem();
            shoppingItem.setProductId(productId);
            shoppingItem.setAmount(amount);
            shoppingItem.setCart(cart);
            cart.getShoppingList().add(shoppingItem);
        }
        else {
            shoppingItem.setAmount(shoppingItem.getAmount() + amount);
        }

        this.shoppingRepository.save(shoppingItem);
        this.repository.save(cart);
        return cart;
    }

    public double payForCart(long cartId) {
        Cart cart = this.repository.findById(cartId).orElseThrow();
        if(cart.isPayed()) {
            throw new BadRequestException();
        }else {
            cart.setPayed(true);
        }

        double price = 0.0;
        for (ShoppingItem item : cart.getShoppingList()) {
            Product product = this.productService.getById(item.getProductId());
            price += product.getPrice() * item.getAmount();
        }
        this.repository.save(cart);
        return price;
    }

}
