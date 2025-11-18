package com.moon.todo.domain.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import com.moon.todo.domain.Priority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "todos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Todo {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(nullable = false)
	private String title;
	@Column(length = 1000)
	private String description;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Priority priority;

	@Column(nullable = false)
	private boolean completed = false;
	@Column(nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();
	@Column(nullable = false)
	private LocalDateTime updatedAt = LocalDateTime.now();

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Todo todo = (Todo)o;
		return completed == todo.completed && Objects.equals(id, todo.id) && Objects.equals(title,
			todo.title) && Objects.equals(description, todo.description) && priority == todo.priority
			&& Objects.equals(createdAt, todo.createdAt) && Objects.equals(updatedAt, todo.updatedAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, description, priority, completed, createdAt, updatedAt);
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}

