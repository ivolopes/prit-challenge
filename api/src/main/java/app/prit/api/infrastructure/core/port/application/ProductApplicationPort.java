package app.prit.api.infrastructure.core.port.application;

import app.prit.api.infrastructure.core.rest.product.v1.dto.ProductDto;

import java.util.List;

public interface ProductApplicationPort {

    ProductDto save(String name, String description, Double price);

    ProductDto update(Integer id, String name, String description, Double price);

    List<ProductDto> findAll(Integer page, Integer limit);

    void delete(Integer id);

}
