package sk.stuba.fei.uim.oop.assignment3.product;

import java.util.List;


public interface IProductService {

    List<Product> getAll();
    Product getAllById(Long id);
    Product getById(Long productId);
    Product createProduct(ProductRequest request);
    Product updateProduct(Long productId,ProductRequest request);
    void deleteProductById(Long id);
    Product increaseAmount(Long id, ProductRequest request);
    void decreaseAmount(Long id, int amount);
}
