package smartbudget.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import smartbudget.dto.UserDto;
import smartbudget.dto.createDto.CreateUserDto;
import smartbudget.enitity.User;
import smartbudget.exception.NotFoundException;
import smartbudget.mapper.UserMapper;
import smartbudget.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private UserMapper userMapper;

	@InjectMocks
	private UserService userService;

	private CreateUserDto createDto;
	private User user;
	private UserDto userDto;

	@BeforeEach
	void setup() {
		createDto = new CreateUserDto();
		createDto.setEmail("test@test.com");
		createDto.setPassword("123");

		user = User.builder()
				.id(1L)
				.email("test@test.com")
				.passwordHash("123")
				.build();

		userDto = new UserDto();
	}

	// =========================
	// CREATE
	// =========================

	@Test
	void shouldCreateUser() {
		when(userRepository.save(any())).thenReturn(user);
		when(userMapper.toDto(any())).thenReturn(userDto);

		UserDto result = userService.create(createDto);

		assertNotNull(result);
		verify(userRepository).save(any());
	}

	// =========================
	// GET BY ID
	// =========================

	@Test
	void shouldReturnUserById() {
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		when(userMapper.toDto(user)).thenReturn(userDto);

		UserDto result = userService.getById(1L);

		assertNotNull(result);
	}

	@Test
	void shouldThrowWhenUserNotFound() {
		when(userRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(NotFoundException.class,
				() -> userService.getById(1L));
	}

	// =========================
	// GET ALL
	// =========================

	@Test
	void shouldReturnAllUsers() {
		when(userRepository.findAll()).thenReturn(List.of(user));
		when(userMapper.toDtoList(any())).thenReturn(List.of(userDto));

		List<UserDto> result = userService.getAll();

		assertEquals(1, result.size());
	}

	// =========================
	// DELETE
	// =========================

	@Test
	void shouldDeleteUser() {
		when(userRepository.existsById(1L)).thenReturn(true);

		userService.delete(1L);

		verify(userRepository).deleteById(1L);
	}

	@Test
	void shouldThrowWhenDeleteNotFound() {
		when(userRepository.existsById(1L)).thenReturn(false);

		assertThrows(NotFoundException.class,
				() -> userService.delete(1L));
	}
}