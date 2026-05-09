package smartbudget.mapper;

import org.mapstruct.Mapper;
import smartbudget.dto.UserDto;
import smartbudget.enitity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDto toDto(User user);

	List<UserDto> toDtoList(List<User> users);
}
