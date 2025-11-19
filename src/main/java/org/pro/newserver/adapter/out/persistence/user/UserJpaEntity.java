package org.pro.newserver.adapter.out.persistence.user;

import org.pro.newserver.domain.user.model.User;
import org.pro.newserver.domain.user.model.Gender;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class UserJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(unique = true, nullable = false)
	private String email;

	private String password;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	public UserJpaEntity(String name, String email, String password, Gender gender) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
	}

	public static UserJpaEntity fromDomain(User user) {
		return new UserJpaEntity(
			user.getName(),
			user.getEmail(),
			user.getPassword(),
			user.getGender()
		);
	}

	public User toDomain() {
		return User.builder()
			.id(id)
			.name(name)
			.email(email)
			.password(password)
			.gender(gender)
			.build();
	}
}
