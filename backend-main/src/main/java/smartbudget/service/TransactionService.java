package smartbudget.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import smartbudget.dto.TransactionDto;
import smartbudget.dto.createDto.CreateTransactionDto;
import smartbudget.enitity.Category;
import smartbudget.enitity.Transaction;
import smartbudget.enitity.User;
import smartbudget.exception.NotFoundException;
import smartbudget.mapper.TransactionMapper;
import smartbudget.repository.CategoryRepository;
import smartbudget.repository.TransactionRepository;
import smartbudget.repository.UserRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

	private final TransactionRepository transactionRepository;
	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;
	private final TransactionMapper transactionMapper;

	public TransactionDto create(final CreateTransactionDto dto) {

		final User user = userRepository.findById(dto.getUserId())
				.orElseThrow(() -> {
					log.error("User not found by id {}", dto.getUserId());
					throw new NotFoundException("User not found");
				});

		final Category category = categoryRepository.findById(dto.getCategoryId())
				.orElseThrow(() -> {
					log.error("Category not found by id: {}", dto.getCategoryId());
					throw new NotFoundException("Category not found");
				});

		final Transaction transaction = Transaction.builder()
				.user(user)
				.category(category)
				.amount(dto.getAmount())
				.type(dto.getType())
				.date(dto.getDate())
				.currency(dto.getCurrency())
				.description(dto.getDescription())
				.build();

		return transactionMapper.toDto(transactionRepository.save(transaction));
	}

	public TransactionDto getById(final Long id) {
		return transactionMapper.toDto(transactionRepository.findById(id)
				.orElseThrow(() -> {
					log.error("Transaction not found by id: {}", id);
					throw new NotFoundException("Transaction not found");
				}));
	}

	public List<TransactionDto> getAll(final Long userId, final Long categoryId) {

		if (userId != null && categoryId != null) {
			return transactionMapper.toDtoList(transactionRepository.findByUserIdAndCategoryId(userId, categoryId));
		}

		if (userId != null) {
			return transactionMapper.toDtoList(transactionRepository.findByUserId(userId));
		}

		if (categoryId != null) {
			return transactionMapper.toDtoList(transactionRepository.findByCategoryId(categoryId));
		}

		return transactionMapper.toDtoList(transactionRepository.findAllWithCategory());
	}

	public void delete(final Long id) {
		if (!transactionRepository.existsById(id)) {
			log.error("Transaction not found by id: {}", id);
			throw new NotFoundException("Transaction not found");
		}
		transactionRepository.deleteById(id);
	}
}
