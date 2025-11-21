package org.pro.newserver.adapter.out.persistence.post;

import lombok.RequiredArgsConstructor;

import org.pro.newserver.adapter.out.persistence.user.UserJpaEntity;
import org.pro.newserver.adapter.out.persistence.user.UserJpaRepository;
import org.pro.newserver.domain.post.infrastructure.PostRepository;
import org.pro.newserver.domain.post.model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

	private final PostJpaRepository postJpaRepository;
	private final PostJpaMapper postJpaMapper;

	@Override
	public Post save(Post post) {
		PostJpaEntity entity = postJpaMapper.toEntity(post);
		PostJpaEntity saved = postJpaRepository.save(entity);
		return postJpaMapper.toDomain(saved);
	}

	@Override
	public Optional<Post> findById(Long id) {
		return postJpaRepository.findById(id)
			.map(postJpaMapper::toDomain);
	}

	@Override
	public List<Post> findAll() {
		return postJpaRepository.findAll().stream()
			.map(postJpaMapper::toDomain)
			.toList();
	}

	@Override
	public void deleteById(Long id) {
		postJpaRepository.deleteById(id);
	}
}


