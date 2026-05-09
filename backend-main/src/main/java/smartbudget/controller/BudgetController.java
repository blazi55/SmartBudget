package smartbudget.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import smartbudget.dto.BudgetDto;
import smartbudget.dto.createDto.CreateBudgetDto;
import smartbudget.service.BudgetService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetController {

	private final BudgetService budgetService;

	@PostMapping
	public ResponseEntity<BudgetDto> create(@RequestBody final CreateBudgetDto dto) {
		log.info("create budget in controller: {}", dto);
		return ResponseEntity.ok(budgetService.create(dto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<BudgetDto> get(@PathVariable final Long id) {
		log.info("create budget in controller by id: {}", id);
		return ResponseEntity.ok(budgetService.getById(id));
	}

	@GetMapping
	public ResponseEntity<List<BudgetDto>> getAll(@RequestParam final Long userId) {
		log.info("get all budgets in controller by userId: {}", userId);
		return ResponseEntity.ok(budgetService.getAll(userId));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable final Long id) {
		log.info("delete budget in controller by id: {}", id);
		budgetService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
