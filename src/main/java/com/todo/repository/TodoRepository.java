package com.todo.repository;

import com.todo.entity.TodoEntity;
import com.todo.type.TodoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    List<TodoEntity> findByStatus(TodoStatus status);
}
