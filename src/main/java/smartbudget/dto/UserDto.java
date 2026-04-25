package smartbudget.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
	private String email;
	private String password;
	private LocalDateTime createdAt;
}
