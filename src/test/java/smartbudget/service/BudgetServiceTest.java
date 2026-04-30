package smartbudget.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import smartbudget.dto.BudgetDto;
import smartbudget.dto.createDto.CreateBudgetDto;
import smartbudget.enitity.Budget;
import smartbudget.enitity.Category;
import smartbudget.enitity.User;
import smartbudget.enums.BudgetPeriod;
import smartbudget.exception.NotFoundException;
import smartbudget.mapper.BudgetMapper;
import smartbudget.repository.BudgetRepository;
import smartbudget.repository.CategoryRepository;
import smartbudget.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BudgetServiceTest {

	@Mock
	private BudgetRepository budgetRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private CategoryRepository categoryRepository;

	@Mock
	private BudgetMapper budgetMapper;

	@InjectMocks
	private BudgetService budgetService;

	private CreateBudgetDto dto;
	private User user;
	private Category category;
	private Budget budget;
	private BudgetDto budgetDto;

	@BeforeEach
	void setup() {
		dto = new CreateBudgetDto();
		dto.setUserId(1L);
		dto.setCategoryId(2L);
		dto.setLimitAmount(BigDecimal.valueOf(500));
		dto.setPeriod(BudgetPeriod.MONTH);
		dto.setStartDate(LocalDate.now());

		user = User.builder().id(1L).build();
		category = Category.builder().id(2L).build();

		budget = Budget.builder()
				.user(user)
				.category(category)
				.limitAmount(dto.getLimitAmount())
				.period(dto.getPeriod())
				.startDate(dto.getStartDate())
				.build();

		budgetDto = new BudgetDto();
	}

	// =========================
	// CREATE
	// =========================

	@Test
	void shouldCreateBudget() {

		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		when(categoryRepository.findById(2L)).thenReturn(Optional.of(category));
		when(budgetRepository.existsByUserIdAndCategoryIdAndPeriod(any(), any(), any()))
				.thenReturn(false);
		when(budgetRepository.save(any())).thenReturn(budget);
		when(budgetMapper.toDto(any())).thenReturn(budgetDto);

		BudgetDto result = budgetService.create(dto);

		assertNotNull(result);
		verify(budgetRepository).save(any());
	}

	@Test
	void shouldThrowWhenUserNotFound() {
		when(userRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(NotFoundException.class, () -> budgetService.create(dto));
	}

	@Test
	void shouldThrowWhenCategoryNotFound() {
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		when(categoryRepository.findById(2L)).thenReturn(Optional.empty());

		assertThrows(NotFoundException.class, () -> budgetService.create(dto));
	}

	@Test
	void shouldThrowWhenBudgetExists() {
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		when(categoryRepository.findById(2L)).thenReturn(Optional.of(category));
		when(budgetRepository.existsByUserIdAndCategoryIdAndPeriod(any(), any(), any()))
				.thenReturn(true);

		assertThrows(IllegalStateException.class, () -> budgetService.create(dto));
	}

	// =========================
	// GET BY ID
	// =========================

	@Test
	void shouldReturnBudgetById() {
		when(budgetRepository.findById(1L)).thenReturn(Optional.of(budget));
		when(budgetMapper.toDto(budget)).thenReturn(budgetDto);

		BudgetDto result = budgetService.getById(1L);

		assertNotNull(result);
	}

	@Test
	void shouldThrowWhenBudgetNotFound() {
		when(budgetRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(NotFoundException.class, () -> budgetService.getById(1L));
	}

	// =========================
	// GET ALL
	// =========================

	@Test
	void shouldReturnAllBudgets() {
		when(budgetRepository.findByUserId(1L)).thenReturn(List.of(budget));
		when(budgetMapper.toDto(budget)).thenReturn(budgetDto);

		List<BudgetDto> result = budgetService.getAll(1L);

		assertEquals(1, result.size());
	}

	// =========================
	// DELETE
	// =========================

	@Test
	void shouldDeleteBudget() {
		when(budgetRepository.existsById(1L)).thenReturn(true);

		budgetService.delete(1L);

		verify(budgetRepository).deleteById(1L);
	}

	@Test
	void shouldThrowWhenDeleteNotFound() {
		when(budgetRepository.existsById(1L)).thenReturn(false);

		assertThrows(NotFoundException.class, () -> budgetService.delete(1L));
	}
}