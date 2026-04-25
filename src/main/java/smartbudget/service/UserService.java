package smartbudget.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smartbudget.dto.UserDto;
import smartbudget.dto.createDto.CreateUserDto;
import smartbudget.enitity.User;
import smartbudget.exception.NotFoundException;
import smartbudget.mapper.UserMapper;
import smartbudget.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public UserDto create(CreateUserDto dto) {
		User user = User.builder()
				.email(dto.getEmail())
				.passwordHash(hashPassword(dto.getPassword()))
				.createdAt(LocalDateTime.now())
				.build();

		return userMapper.toDto(userRepository.save(user));
	}

	public UserDto getById(Long id) {
		return userMapper.toDto(userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("User not found")));
	}

	public List<UserDto> getAll() {
		return userMapper.toDtoList(userRepository.findAll());
	}

	public void delete(Long id) {
		if (!userRepository.existsById(id)) {
			throw new NotFoundException("User not found");
		}
		userRepository.deleteById(id);
	}

	private String hashPassword(String password) {
		// TODO: BCrypt
		return password;
	}
}
