package ob.poc.msproducts.utils.mapper;

import ob.poc.msproducts.entity.Product;
import ob.poc.msproducts.entity.dto.request.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto productToProductDto(Product entity);

    Product productDtoToProductEntity(ProductDto dto);

    List<ProductDto> productListToProductDtoList(List<Product> entities);


    Product updateProductFromDto(@MappingTarget Product oldProduct, ProductDto newProductDto);
}
