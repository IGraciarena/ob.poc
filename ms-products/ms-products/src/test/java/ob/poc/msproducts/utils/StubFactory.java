package ob.poc.msproducts.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import ob.poc.msproducts.entity.Category;
import ob.poc.msproducts.entity.Product;
import ob.poc.msproducts.entity.dto.request.CategoryDto;
import ob.poc.msproducts.entity.enums.ProductStatus;

import java.util.List;

public interface StubFactory {
    default Product createProductStub() {
        return Product.builder()
                .id(1L)
                .name("productStubName")
                .description("productStubDescription")
                .price(100D)
                .stock(100D)
                .status(ProductStatus.CREATED)
                .category(createCategoryStub())
                .build();
    }

    default Category createCategoryStub() {
        return Category.builder()
                .id(1L)
                .name("categoryStubName")
                .build();
    }

    default Product createProductStubNoCategory() {
        return Product.builder()
                .id(1L)
                .name("productStubName")
                .description("productStubDescription")
                .price(100D)
                .stock(100D)
                .status(ProductStatus.CREATED)
                .category(new Category())
                .build();
    }

    default CategoryDto createCategoryDtoStub() {
        return CategoryDto.builder().name("categoryDtoStubName").build();
    }

    default String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default List<Product> createProductsStub() {
        return List.of(Product.builder()
                        .id(1L)
                        .name("productStubName")
                        .description("productStubDescription")
                        .price(100D)
                        .stock(100D)
                        .status(ProductStatus.CREATED)
                        .category(createCategoryStub())
                        .build(),
                Product.builder()
                        .id(2L)
                        .name("productStubName1")
                        .description("productStubDescription1")
                        .price(100D)
                        .stock(100D)
                        .status(ProductStatus.CREATED)
                        .category(createCategoryStub())
                        .build(),
                Product.builder()
                        .id(3L)
                        .name("productStubName2")
                        .description("productStubDescription2")
                        .price(100D)
                        .stock(100D)
                        .status(ProductStatus.CREATED)
                        .category(createCategoryStub())
                        .build(),
                Product.builder()
                        .id(4L)
                        .name("productStubName2")
                        .description("productStubDescription2")
                        .price(100D)
                        .stock(100D)
                        .status(ProductStatus.CREATED)
                        .category(Category.builder().id(2L).name("categoryStubName2").build())
                        .build());
    }
}
