package smartbudget.controller;

import lombok.RequiredArgsConstructor;
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

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetController {

	private final BudgetService budgetService;

	@PostMapping
	public ResponseEntity<BudgetDto> create(@RequestBody CreateBudgetDto dto) {
		return ResponseEntity.ok(budgetService.create(dto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<BudgetDto> get(@PathVariable Long id) {
		return ResponseEntity.ok(budgetService.getById(id));
	}

	@GetMapping
	public ResponseEntity<List<BudgetDto>> getAll(@RequestParam Long userId) {
		return ResponseEntity.ok(budgetService.getAll(userId));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		budgetService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
