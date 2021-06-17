package ob.poc.msproducts.utils.mapper;

import ob.poc.msproducts.entity.Category;
import ob.poc.msproducts.entity.dto.request.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto categoryToCategoryDto(Category entity);

    @Mapping(target = "id" , ignore = true)
    Category categoryDtoToCategoryEntity(CategoryDto dto);

}
