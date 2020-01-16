package app.prit.api.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor(onConstructor_ = @Builder)
@Entity
@Table(name = "product")
public class Product {

    @Setter(AccessLevel.PRIVATE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Setter(AccessLevel.PRIVATE)
    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @Setter(AccessLevel.PRIVATE)
    @NonNull
    @Column(name = "description", nullable = false)
    private String description;

    @Setter(AccessLevel.PRIVATE)
    @NonNull
    @Column(name = "price", nullable = false)
    private Double price;

    @Builder.Default
    @Setter(AccessLevel.PRIVATE)
    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Deprecated
    Product(){

    }

    public void update(String name, String description, Double price){
        this.name = Objects.requireNonNull(name, "Name is required");
        this.description = Objects.requireNonNull(description, "Description is required");
        this.price = Objects.requireNonNull(price, "Price is required");
    }

    public void inactivate(){
        this.active = false;
    }

    public static Product of(String name, String description, Double price){
        return Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .build();
    }
}
