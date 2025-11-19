package org.pro.newserver.adapter.out.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {

	Optional<UserJpaEntity> findByEmail(String email);

	Optional<UserJpaEntity> findByNameAndPassword(String name, String password);

	boolean existsByEmail(String email);
}