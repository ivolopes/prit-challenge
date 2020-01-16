package app.prit.api.infrastructure.core.rest.user.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor(onConstructor_ = @Builder)
public class UserDto {

    private Integer id;

    private String name;

    private String email;

    private String password;

    public UserDto(){

    }

}
