package app.prit.api.infrastructure.core.repository;

import app.prit.api.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, Integer> {

    Page<Product> findAll(Pageable pagination);

}
