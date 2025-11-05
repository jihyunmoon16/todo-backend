package com.moon.todo.domain.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pomodoros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pomodoro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Integer duration;
	@Column(nullable = false)
	private LocalDateTime completedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "todo_id")
	private Todo todo;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Pomodoro pomodoro = (Pomodoro)o;
		return Objects.equals(id, pomodoro.id) && Objects.equals(duration, pomodoro.duration)
			&& Objects.equals(completedAt, pomodoro.completedAt) && Objects.equals(todo, pomodoro.todo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, duration, completedAt, todo);
	}

	@PrePersist
	protected void onCreate() {
		this.completedAt = LocalDateTime.now();
	}
}
