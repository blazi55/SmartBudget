package smartbudget.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import smartbudget.dto.UserDto;
import smartbudget.dto.createDto.CreateUserDto;
import smartbudget.enitity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(target = "passwordHash", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "id", ignore = true)
	User toEntity(CreateUserDto dto);

	UserDto toDto(User user);

	List<UserDto> toDtoList(List<User> users);
}
