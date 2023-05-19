package sk.stuba.fei.uim.oop.assignment3.shoppinglist;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingRepository extends CrudRepository<ShoppingItem, Long> {
    Optional<ShoppingItem> findById(Long id);
    List<ShoppingItem> findAll();
}
