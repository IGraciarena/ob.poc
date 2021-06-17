package ob.poc.msproducts.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import ob.poc.msproducts.entity.dto.request.CategoryDto;
import ob.poc.msproducts.service.CategoryServiceImpl;
import ob.poc.msproducts.utils.uri.RestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/categories")
@AllArgsConstructor
public class CategoryController {

    @Autowired
    private final CategoryServiceImpl service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Register a new Category.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success."),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Already Exists."),
            @ApiResponse(code = 404, message = "Something went wrong.")
    })
    public ResponseEntity<URI> registerCategory(@Valid @RequestBody CategoryDto dto) {
        return ResponseEntity.created(
                RestUtils.getLocationCategory(service.registerCategory(dto)))
                .build();
    }
}
