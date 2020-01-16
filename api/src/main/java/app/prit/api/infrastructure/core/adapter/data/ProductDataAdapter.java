package app.prit.api.infrastructure.core.adapter.data;

import app.prit.api.domain.entity.Product;
import app.prit.api.infrastructure.core.port.data.ProductDataPort;
import app.prit.api.infrastructure.core.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDataAdapter implements ProductDataPort {

    private ProductRepository repository;

    @Autowired
    public ProductDataAdapter(ProductRepository repository){
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public Product update(Product product) {
        return repository.save(product);
    }

    @Override
    public Page<Product> findAll(Integer page, Integer limit) {
        Pageable pagination = PageRequest.of(page, limit);
        return repository.findAll(pagination);
    }

    @Override
    public Product findById(Integer id){
        return repository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
