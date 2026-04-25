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
import smartbudget.dto.TransactionDto;
import smartbudget.dto.createDto.CreateTransactionDto;
import smartbudget.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

	private final TransactionService transactionService;

	@PostMapping
	public ResponseEntity<TransactionDto> create(@RequestBody CreateTransactionDto dto) {
		return ResponseEntity.ok(transactionService.create(dto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<TransactionDto> get(@PathVariable Long id) {
		return ResponseEntity.ok(transactionService.getById(id));
	}

	@GetMapping
	public ResponseEntity<List<TransactionDto>> getAll(@RequestParam(required = false) Long userId, @RequestParam(required = false) Long categoryId) {
		return ResponseEntity.ok(transactionService.getAll(userId, categoryId));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		transactionService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
