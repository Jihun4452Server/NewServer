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
}
