package smartbudget.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import smartbudget.dto.CategoryDto;
import smartbudget.dto.createDto.CreateCategoryDto;
import smartbudget.enitity.Category;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	CategoryDto toDto(Category category);

	List<CategoryDto> toDtoList(List<Category> categories);

	@Mapping(target = "id", ignore = true)
	Category toEntity(CreateCategoryDto dto);
}
