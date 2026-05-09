package smartbudget.mapper;

import org.mapstruct.Mapper;
import smartbudget.dto.CategoryDto;
import smartbudget.enitity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	CategoryDto toDto(Category category);

}
