package smartbudget.dto;

import lombok.Data;
import smartbudget.enums.CategoryType;

@Data
public class CategoryDto {

	private String name;
	private CategoryType type;
	private String icon;
	private String color;
}
