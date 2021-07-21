package com.todo.util;

import com.todo.entity.TodoEntity;
import com.todo.model.TodoResponse;
import com.todo.repository.TodoRepository;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class TodoUtils {


    public static List<TodoResponse> findList(ModelMapper mapper, List<TodoEntity> entities) {
        return entities.isEmpty() ? new ArrayList<TodoResponse>()
                : entities
                .stream()
                .map(todo -> mapper.map(todo, TodoResponse.class))
                .collect(Collectors.toList());
    }

    public static Optional<TodoEntity> findTodoById(TodoRepository repository, Long id) {
        return repository.findById(id);
    }
}
