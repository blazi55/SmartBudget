package smartbudget.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smartbudget.dto.BudgetDto;
import smartbudget.dto.createDto.CreateBudgetDto;
import smartbudget.enitity.Budget;
import smartbudget.enitity.Category;
import smartbudget.enitity.User;
import smartbudget.exception.NotFoundException;
import smartbudget.mapper.BudgetMapper;
import smartbudget.repository.BudgetRepository;
import smartbudget.repository.CategoryRepository;
import smartbudget.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetService {

	private final BudgetRepository budgetRepository;
	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;
	private final BudgetMapper budgetMapper;

	public BudgetDto create(CreateBudgetDto dto) {

		User user = userRepository.findById(dto.getUserId())
				.orElseThrow(() -> new NotFoundException("User not found"));

		Category category = categoryRepository.findById(dto.getCategoryId())
				.orElseThrow(() -> new NotFoundException("Category not found"));

		boolean exists = budgetRepository.existsByUserIdAndCategoryIdAndPeriod(
				dto.getUserId(),
				dto.getCategoryId(),
				dto.getPeriod()
		);

		if (exists) {
			throw new IllegalStateException("Budget already exists for this category and period");
		}

		Budget budget = Budget.builder()
				.user(user)
				.category(category)
				.limitAmount(dto.getLimitAmount())
				.period(dto.getPeriod())
				.startDate(dto.getStartDate())
				.build();

		return budgetMapper.toDto(budgetRepository.save(budget));
	}

	public BudgetDto getById(Long id) {
		return budgetMapper.toDto(budgetRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Budget not found")));
	}

	public List<BudgetDto> getAll(Long userId) {
		return budgetRepository.findByUserId(userId)
				.stream()
				.map(budgetMapper::toDto)
				.toList();
	}

	public void delete(Long id) {
		if (!budgetRepository.existsById(id)) {
			throw new NotFoundException("Budget not found");
		}
		budgetRepository.deleteById(id);
	}
}
