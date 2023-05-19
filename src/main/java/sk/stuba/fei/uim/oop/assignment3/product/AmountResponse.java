package sk.stuba.fei.uim.oop.assignment3.product;

import lombok.Getter;

@Getter
public class AmountResponse {
    private final int amount;

    public AmountResponse(Product p) {
        this.amount = p.getAmount();
    }
}
