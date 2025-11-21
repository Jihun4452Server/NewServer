package org.pro.newserver.domain.post.infrastructure;

import org.pro.newserver.domain.post.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

	Post save(Post post);

	Optional<Post> findById(Long id);

	List<Post> findAll();

	void deleteById(Long id);
}
