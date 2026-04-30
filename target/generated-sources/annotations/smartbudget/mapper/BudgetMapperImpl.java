package smartbudget.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import smartbudget.dto.BudgetDto;
import smartbudget.enitity.Budget;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-30T18:21:05+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class BudgetMapperImpl implements BudgetMapper {

    @Override
    public BudgetDto toDto(Budget budget) {
        if ( budget == null ) {
            return null;
        }

        BudgetDto budgetDto = new BudgetDto();

        return budgetDto;
    }
}
