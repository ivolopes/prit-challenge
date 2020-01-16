package app.prit.api.infrastructure.core.rest.product.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(onConstructor_ = @Builder)
public class ProductDto {

    private Integer id;

    private String name;

    private String description;

    private Double price;

}
