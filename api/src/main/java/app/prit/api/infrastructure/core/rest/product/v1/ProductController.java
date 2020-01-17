package app.prit.api.infrastructure.core.rest.product.v1;

import app.prit.api.infrastructure.core.port.application.ProductApplicationPort;
import app.prit.api.infrastructure.core.rest.product.v1.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/products",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE
        })
public class ProductController {


        private ProductApplicationPort productApplication;

        @Autowired
        public ProductController(ProductApplicationPort productApplication){
                this.productApplication = productApplication;
        }

        @PostMapping
        public ResponseEntity<ProductDto> add(@RequestBody ProductDto productDto){
             ProductDto product = productApplication.save(productDto.getName(),
                                                                productDto.getDescription(),
                                                                productDto.getPrice());
             URI uri = URI.create("/api/v1/products/"+product.getId());

             return ResponseEntity.created(uri).body(product);
        }

        @GetMapping
        public ResponseEntity<List<ProductDto>> getAll(@RequestParam(required = false) Integer limit,
                                                       @RequestParam(required = false) Integer page){

                limit = limit != null ? limit : 20;
                page = page != null ? page : 0;

                List<ProductDto> productDtoList = productApplication.findAll(page, limit);

                return ResponseEntity.ok(productDtoList);
        }

        @PutMapping("/{id}")
        public ResponseEntity<ProductDto> update(@PathVariable Integer id,
                                                 @RequestBody ProductDto productDto){
                ProductDto product = productApplication.update(id,
                        productDto.getName(),
                        productDto.getDescription(),
                        productDto.getPrice());

                return ResponseEntity.ok(product);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> delete(@PathVariable Integer id){
                productApplication.delete(id);

                return ResponseEntity.noContent().build();
        }
}
