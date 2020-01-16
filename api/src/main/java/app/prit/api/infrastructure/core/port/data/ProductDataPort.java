package app.prit.api.infrastructure.core.port.data;

import app.prit.api.domain.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductDataPort {

    Product save(Product product);

    Product update(Product product);

    Page<Product> findAll(Integer page, Integer limit);

    Product findById(Integer id);

    void delete(Integer id);

}
