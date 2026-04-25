package smartbudget.dto.createDto;

import lombok.Data;
import smartbudget.enums.CategoryType;

@Data
public class CreateCategoryDto {

	private String name;
	private CategoryType type;
	private String icon;
	private String color;
}
