package org.pro.newserver.domain.post.infrastructure;

import org.pro.newserver.domain.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
