package sk.stuba.fei.uim.oop.assignment3.cart;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.shoppinglist.ShoppingItemRequest;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService service;

    @GetMapping()
    public List<CartResponse> getAllCart(){
        return this.service.getAll().stream().map(CartResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    public ResponseEntity<CartResponse> addCart(){
        return new ResponseEntity<>(new CartResponse(this.service.createCart()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public CartResponse getAllCartsById(@PathVariable("id") Long id){
        return new CartResponse(this.service.getAllById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable("id") Long cartId){
        this.service.deleteCartById(cartId);
    }

    @PostMapping("/{id}/add")
    public ResponseEntity<CartResponse> addProductToCart(@PathVariable("id") long cartId, @RequestBody ShoppingItemRequest shoppingListRequest){
        return new ResponseEntity<>( new CartResponse(this.service.addProductToCart(cartId, shoppingListRequest.getProductId(),shoppingListRequest.getAmount())), HttpStatus.OK);
    }

    @GetMapping("/{id}/pay")
    public  ResponseEntity<String> payCart(@PathVariable("id") long cartId){
        String resp = String.valueOf(this.service.payForCart(cartId));
        return new ResponseEntity<>(resp, HttpStatus.OK);

    }


}
