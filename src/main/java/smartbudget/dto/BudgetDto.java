package smartbudget.dto;

import lombok.Data;
import smartbudget.enums.BudgetPeriod;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BudgetDto {
	private BigDecimal limitAmount;
	private BudgetPeriod period;
	private LocalDate startDate;
}
