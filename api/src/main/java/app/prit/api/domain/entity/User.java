package app.prit.api.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor(onConstructor_ = @Builder)
@Entity
@Table(name = "user")
public class User {

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
    @Column(name = "email", nullable = false)
    private String email;

    @Setter(AccessLevel.PRIVATE)
    @NonNull
    @Column(name = "password", nullable = false)
    private String password;

    @Setter(AccessLevel.PRIVATE)
    @Builder.Default
    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Deprecated
    User(){

    }

}
