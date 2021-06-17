package ob.poc.msproducts.service;

import lombok.RequiredArgsConstructor;
import ob.poc.msproducts.entity.Category;
import ob.poc.msproducts.entity.dto.request.CategoryDto;
import ob.poc.msproducts.repository.CategoryRepository;
import ob.poc.msproducts.utils.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper = CategoryMapper.INSTANCE;

    @Override
    public Category registerCategory(CategoryDto dto) {
        return repository.save(mapper.categoryDtoToCategoryEntity(dto));
    }
}
