package app.prit.api.application.core;

import app.prit.api.domain.entity.Product;
import app.prit.api.infrastructure.core.port.application.ProductApplicationPort;
import app.prit.api.infrastructure.core.port.data.ProductDataPort;
import app.prit.api.infrastructure.core.rest.product.v1.dto.ProductDto;
import app.prit.api.infrastructure.exceptions.NotAcceptableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductApplication implements ProductApplicationPort {

    private ProductDataPort productData;

    @Autowired
    public ProductApplication(ProductDataPort productData){
        this.productData = productData;
    }

    @Override
    public ProductDto save(String name, String description, Double price) {

        Product product = Product.of(name, description, price);
        product = productData.save(product);

        return convertToProductDto(product);
    }

    @Override
    public ProductDto update(Integer id, String name, String description, Double price) {
        Product product = productData.findById(id);

        if( product == null ){
            throw new NotAcceptableException("Product not found for id "+id);
        }

        product.update(name, description, price);

        product = productData.update(product);

        return convertToProductDto(product);
    }

    @Override
    public List<ProductDto> findAll(Integer page, Integer limit) {
        final Page<Product> products = productData.findAll(page, limit);

        return convertToProductDto(products);
    }

    @Override
    public void delete(Integer id) {
        productData.delete(id);
    }

    private ProductDto convertToProductDto(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    private List<ProductDto> convertToProductDto(Page<Product> products){

        List<ProductDto> list = new ArrayList<>();

        products.iterator().forEachRemaining(product -> {
            ProductDto aux = ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .build();

            list.add(aux);
        });

        return list;
    }
}
