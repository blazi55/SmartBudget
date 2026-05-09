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
import org.springframework.web.bind.annotation.RestController;
import smartbudget.dto.CategoryDto;
import smartbudget.dto.createDto.CreateCategoryDto;
import smartbudget.service.CategoryService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	@PostMapping
	public ResponseEntity<CategoryDto> create(@RequestBody CreateCategoryDto dto) {
		log.info("create category in controller: {}", dto);
		return ResponseEntity.ok(categoryService.create(dto));
	}

	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAll() {
		return ResponseEntity.ok(categoryService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> get(@PathVariable Long id) {
		log.info("create category in controller by id: {}", id);
		return ResponseEntity.ok(categoryService.getById(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		log.info("delete category in controller by id: {}", id);
		categoryService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
