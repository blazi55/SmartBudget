package smartbudget.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import smartbudget.dto.TransactionDto;
import smartbudget.dto.createDto.CreateTransactionDto;
import smartbudget.enitity.Transaction;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "user", ignore = true)
	@Mapping(target = "category", ignore = true)
	Transaction toEntity(CreateTransactionDto dto);

	@Mapping(target = "userId", source = "user.id")
	@Mapping(target = "categoryId", source = "category.id")
	TransactionDto toDto(Transaction transaction);

	List<TransactionDto> toDtoList(List<Transaction> transactions);
}
