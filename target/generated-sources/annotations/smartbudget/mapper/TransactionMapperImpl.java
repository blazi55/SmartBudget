package smartbudget.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import smartbudget.dto.TransactionDto;
import smartbudget.enitity.Transaction;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-30T18:22:14+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public TransactionDto toDto(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        TransactionDto transactionDto = new TransactionDto();

        transactionDto.setAmount( transaction.getAmount() );
        transactionDto.setType( transaction.getType() );
        transactionDto.setDate( transaction.getDate() );
        transactionDto.setCurrency( transaction.getCurrency() );
        transactionDto.setDescription( transaction.getDescription() );

        return transactionDto;
    }

    @Override
    public List<TransactionDto> toDtoList(List<Transaction> transactions) {
        if ( transactions == null ) {
            return null;
        }

        List<TransactionDto> list = new ArrayList<TransactionDto>( transactions.size() );
        for ( Transaction transaction : transactions ) {
            list.add( toDto( transaction ) );
        }

        return list;
    }
}
