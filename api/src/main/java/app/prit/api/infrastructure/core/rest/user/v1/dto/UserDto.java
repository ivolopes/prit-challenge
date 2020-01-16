package app.prit.api.infrastructure.core.rest.user.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(onConstructor_ = @Builder)
public class UserDto {

    private Integer id;

    private String email;

    private String password;

}
