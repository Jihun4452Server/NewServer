package org.pro.newserver.adapter.out.persistence.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {

  Optional<UserJpaEntity> findByEmail(String email);

  boolean existsByEmail(String email);
}
