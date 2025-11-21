package org.pro.newserver.domain.post.infrastructure;

import java.util.List;
import java.util.Optional;
import org.pro.newserver.domain.post.model.Post;

public interface PostRepository {

  Post save(Post post);

  Optional<Post> findById(Long id);

  List<Post> findAll();

  void deleteById(Long id);
}
