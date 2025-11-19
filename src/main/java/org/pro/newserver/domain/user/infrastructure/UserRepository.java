package org.pro.newserver.domain.user.infrastructure;

import java.util.Optional;

import org.pro.newserver.domain.user.model.User;

public interface UserRepository {

	User save(User user);

	Optional<User> findById(Long id);

	Optional<User> findByEmail(String email);

	Optional<User> findByNameAndPassword(String name, String password);

	boolean existsByEmail(String email);
}