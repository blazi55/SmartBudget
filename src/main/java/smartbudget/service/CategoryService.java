package smartbudget.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smartbudget.dto.CategoryDto;
import smartbudget.dto.createDto.CreateCategoryDto;
import smartbudget.enitity.Category;
import smartbudget.exception.NotFoundException;
import smartbudget.mapper.CategoryMapper;
import smartbudget.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;

	public CategoryDto create(CreateCategoryDto categoryDto) {
		Category category = Category.builder()
				.color(categoryDto.getColor())
				.icon(categoryDto.getIcon())
				.name(categoryDto.getName())
				.type(categoryDto.getType())
				.build();
		final Category save = categoryRepository.save(category);
		return categoryMapper.toDto(save);
	}

	public List<CategoryDto> getAll() {
		return categoryRepository.findAll().stream()
				.map(categoryMapper::toDto)
				.toList();
	}

	public CategoryDto getById(Long id) {
		return categoryMapper.toDto(categoryRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Category not found")));
	}

	public void delete(Long id) {
		if (!categoryRepository.existsById(id)) {
			throw new NotFoundException("Category not found");
		}
		categoryRepository.deleteById(id);
	}
}
