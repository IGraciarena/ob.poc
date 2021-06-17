package ob.poc.msproducts.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import ob.poc.msproducts.entity.Product;
import ob.poc.msproducts.entity.dto.request.ProductDto;
import ob.poc.msproducts.service.ProductServiceImpl;
import ob.poc.msproducts.utils.uri.RestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
@AllArgsConstructor
public class ProductController {

    @Autowired
    private final ProductServiceImpl service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Register a new Product.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created."),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Already Exists."),
            @ApiResponse(code = 404, message = "Something went wrong.")
    })
    public ResponseEntity<URI> registerProduct(@Valid @RequestBody Product product) {
        return ResponseEntity.created(
                RestUtils.getLocationProduct(service.registerProduct(product)))
                .build();
    }

    @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Gets an specific product by the ID.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success."),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 404, message = "Product name not exists.")
    })
    public ResponseEntity<ProductDto> getProductById(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok(service.getProductById(productId));
    }

    @GetMapping(value = "/category/{categoryName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all products from an specific Category")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Success."),
            @ApiResponse(code = 204, message = "No Content."),
    })
    public ResponseEntity<List<ProductDto>> getAllByCategoryName(@PathVariable("categoryName") String categoryName) {
        return ResponseEntity.ok(service.getAllProductsByCategoryName(categoryName));
    }

    @PatchMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update a products, partially.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success."),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 404, message = "Something went wrong.")
    })
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId, @RequestBody @Valid ProductDto dto) {
        service.updateProduct(productId, dto);
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).build();
    }

    @ApiOperation(value = "Unregistered a product.")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Is Accepted."),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 404, message = "Something went wrong.")
    })
    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        service.deleteProduct(productId);
        return ResponseEntity.accepted().build();
    }
}
