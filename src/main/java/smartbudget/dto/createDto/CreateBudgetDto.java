package smartbudget.dto.createDto;

import lombok.Data;
import smartbudget.enums.BudgetPeriod;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateBudgetDto {
	private Long userId;
	private Long categoryId;
	private BigDecimal limitAmount;
	private BudgetPeriod period;
	private LocalDate startDate;
}
