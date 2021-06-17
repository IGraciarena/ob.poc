package ob.poc.msproducts.utils.uri;

import ob.poc.msproducts.entity.Category;
import ob.poc.msproducts.entity.Product;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class RestUtils {
    public static URI getLocationProduct(Product product) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();
    }
    public static URI getLocationCategory(Category category) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId())
                .toUri();
    }
}
