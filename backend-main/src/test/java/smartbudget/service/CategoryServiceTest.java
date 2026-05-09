package smartbudget.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import smartbudget.dto.CategoryDto;
import smartbudget.dto.createDto.CreateCategoryDto;
import smartbudget.enitity.Category;
import smartbudget.enums.CategoryType;
import smartbudget.exception.NotFoundException;
import smartbudget.mapper.CategoryMapper;
import smartbudget.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

	@Mock
	private CategoryRepository categoryRepository;

	@Mock
	private CategoryMapper categoryMapper;

	@InjectMocks
	private CategoryService categoryService;

	private Category category;
	private CategoryDto categoryDto;
	private CreateCategoryDto createDto;

	@BeforeEach
	void setup() {
		createDto = new CreateCategoryDto();
		createDto.setName("Food");
		createDto.setType(CategoryType.EXPENSE);
		createDto.setIcon("utensils");
		createDto.setColor("#f97316");

		category = Category.builder()
				.id(1L)
				.name("Food")
				.type(CategoryType.EXPENSE)
				.icon("utensils")
				.color("#f97316")
				.build();

		categoryDto = new CategoryDto();
	}

	// =========================
	// CREATE
	// =========================

	@Test
	void shouldCreateCategory() {
		when(categoryRepository.save(any())).thenReturn(category);
		when(categoryMapper.toDto(category)).thenReturn(categoryDto);

		CategoryDto result = categoryService.create(createDto);

		assertNotNull(result);
		verify(categoryRepository).save(any());
		verify(categoryMapper).toDto(category);
	}

	// =========================
	// GET ALL
	// =========================

	@Test
	void shouldReturnAllCategories() {
		when(categoryRepository.findAll()).thenReturn(List.of(category));
		when(categoryMapper.toDto(category)).thenReturn(categoryDto);

		List<CategoryDto> result = categoryService.getAll();

		assertEquals(1, result.size());
		verify(categoryRepository).findAll();
	}

	// =========================
	// GET BY ID
	// =========================

	@Test
	void shouldReturnCategoryById() {
		when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
		when(categoryMapper.toDto(category)).thenReturn(categoryDto);

		CategoryDto result = categoryService.getById(1L);

		assertNotNull(result);
	}

	@Test
	void shouldThrowWhenCategoryNotFound() {
		when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(NotFoundException.class,
				() -> categoryService.getById(1L));
	}

	// =========================
	// DELETE
	// =========================

	@Test
	void shouldDeleteCategory() {
		when(categoryRepository.existsById(1L)).thenReturn(true);

		categoryService.delete(1L);

		verify(categoryRepository).deleteById(1L);
	}

	@Test
	void shouldThrowWhenDeleteNotFound() {
		when(categoryRepository.existsById(1L)).thenReturn(false);

		assertThrows(NotFoundException.class,
				() -> categoryService.delete(1L));
	}
}