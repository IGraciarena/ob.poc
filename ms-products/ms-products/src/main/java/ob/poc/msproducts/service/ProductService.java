package ob.poc.msproducts.service;

import ob.poc.msproducts.entity.Product;
import ob.poc.msproducts.entity.dto.request.ProductDto;

import java.util.List;

public interface ProductService {
    Product registerProduct(Product product);
    ProductDto getProductById(Long productId);
    List<ProductDto> getAllProductsByCategoryName(String categoryName);
    Product updateProduct(Long id, ProductDto dto);
    void deleteProduct(Long productId);
}
