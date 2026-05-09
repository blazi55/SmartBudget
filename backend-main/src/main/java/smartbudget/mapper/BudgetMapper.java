package smartbudget.mapper;

import org.mapstruct.Mapper;
import smartbudget.dto.BudgetDto;
import smartbudget.enitity.Budget;

@Mapper(componentModel = "spring")
public interface BudgetMapper {

	BudgetDto toDto(Budget budget);
}
