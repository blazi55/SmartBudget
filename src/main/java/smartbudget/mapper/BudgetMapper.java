package smartbudget.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import smartbudget.dto.BudgetDto;
import smartbudget.dto.createDto.CreateBudgetDto;
import smartbudget.enitity.Budget;

@Mapper(componentModel = "spring")
public interface BudgetMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "user", ignore = true)
	@Mapping(target = "category", ignore = true)
	Budget toEntity(CreateBudgetDto dto);

	BudgetDto toDto(Budget budget);
}
