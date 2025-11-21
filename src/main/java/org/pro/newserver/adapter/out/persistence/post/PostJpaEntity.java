package org.pro.newserver.adapter.out.persistence.post;

import jakarta.persistence.*;
import lombok.*;
import org.pro.newserver.adapter.out.persistence.user.UserJpaEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PostJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public void setAuthor(UserJpaEntity author) {
		this.author = author;
	}
}
