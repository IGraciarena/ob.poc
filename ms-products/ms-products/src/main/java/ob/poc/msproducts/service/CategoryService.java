package ob.poc.msproducts.service;

import ob.poc.msproducts.entity.Category;
import ob.poc.msproducts.entity.dto.request.CategoryDto;

public interface CategoryService {
    Category registerCategory(CategoryDto dto);
}
