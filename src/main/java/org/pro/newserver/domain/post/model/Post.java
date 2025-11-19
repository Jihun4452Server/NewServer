package org.pro.newserver.domain.post.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import org.pro.newserver.adapter.out.persistence.user.UserJpaEntity;
import org.pro.newserver.domain.user.model.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Post {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@Lob
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserJpaEntity author;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public void update(String title, String content) {
		this.title = title;
		this.content = content;
		this.updatedAt = LocalDateTime.now();
	}
}
