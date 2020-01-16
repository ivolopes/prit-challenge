package app.prit.api.infrastructure.core.rest.user.v1;

import app.prit.api.infrastructure.core.port.application.UserApplicationPort;
import app.prit.api.infrastructure.core.rest.product.v1.dto.ProductDto;
import app.prit.api.infrastructure.core.rest.user.v1.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/users",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE
        })
public class UserController {

        private UserApplicationPort userApplication;

        @Autowired
        public UserController(UserApplicationPort userApplication){
                this.userApplication = userApplication;
        }

        @PostMapping
        public ResponseEntity<UserDto> add(@RequestBody UserDto userDto){
                UserDto dto = userApplication.save(userDto.getName(), userDto.getEmail(), userDto.getPassword());

                URI uri = URI.create("/api/v1/users/"+dto.getId());

                return ResponseEntity.created(uri).body(dto);
        }

}
