package ob.poc.msproducts.service;

import lombok.RequiredArgsConstructor;
import ob.poc.msproducts.entity.Product;
import ob.poc.msproducts.entity.dto.request.ProductDto;
import ob.poc.msproducts.entity.enums.ProductStatus;
import ob.poc.msproducts.repository.ProductRepository;
import ob.poc.msproducts.utils.exceptions.NotFoundException;
import ob.poc.msproducts.utils.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static ob.poc.msproducts.utils.exceptions.constants.Constants.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository repository;
    private final ProductMapper mapper = ProductMapper.INSTANCE;

    @Override
    public Product registerProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Optional<Product> entity = Optional.of(repository.findById(productId))
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_PRODUCT_NAME));
        return mapper.productToProductDto(entity.get());
    }

    @Override
    public List<ProductDto> getAllProductsByCategoryName(String categoryName) {
        List<Product> productEntities = Optional.ofNullable(repository.findAllByCategoryName(categoryName))
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_CATEGORY_NAME));
        return mapper.productListToProductDtoList(productEntities);
    }

    @Override
    public Product updateProduct(Long id, ProductDto dto) {
        var oldProduct = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_PRODUCT_ID));
        return repository.save(mapper.updateProductFromDto(oldProduct, dto));
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = Optional.of(repository.findById(productId))
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_PRODUCT_ID)).get();
        product.setStatus(ProductStatus.DELETED);
        repository.save(product);
    }
}
