package sk.stuba.fei.uim.oop.assignment3.shoppinglist;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.cart.Cart;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class ShoppingItem {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Cart cart;

    private Long productId;
    private int amount;
}