package smartbudget.mapper;

import org.mapstruct.Mapper;
import smartbudget.dto.TransactionDto;
import smartbudget.enitity.Transaction;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

	TransactionDto toDto(Transaction transaction);

	List<TransactionDto> toDtoList(List<Transaction> transactions);
}
