package smartbudget.enitity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
	}
}
