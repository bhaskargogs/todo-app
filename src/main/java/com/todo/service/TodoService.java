package com.todo.service;

import com.todo.entity.TodoEntity;
import com.todo.exception.InvalidConstraintException;
import com.todo.exception.NotFoundException;
import com.todo.model.CreateTodoRequest;
import com.todo.model.TodoResponse;
import com.todo.repository.TodoRepository;
import com.todo.type.TodoStatus;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository repository;
    private final ModelMapper mapper;

    public TodoResponse create(CreateTodoRequest createTodoRequest) {
        TodoResponse response;
        try {
            TodoEntity entity = new TodoEntity(createTodoRequest.getDescription(), LocalDateTime.now(), TodoStatus.NOT_DONE,
                    createTodoRequest.getDueDate(), LocalDateTime.now());
            entity = repository.save(entity);
            response = mapper.map(entity, TodoResponse.class);
            response.setId(entity.getId());
        } catch (InvalidConstraintException e) {
            throw new InvalidConstraintException("TodoService.create(): Invalid Constraints - " + e.getMessage());
        }
        return response;
    }

    public TodoResponse findById(Long id) {
        return mapper.map(findTodoById(id)
                .orElseThrow(() -> new NotFoundException("id", String.valueOf(id))), TodoResponse.class);
    }

    public List<TodoResponse> findAllTodos() {
        return findList(repository.findAll());
    }

    public List<TodoResponse> findByStatus(TodoStatus status) {
        return findList(repository.findByStatus(status));
    }

    private List<TodoResponse> findList(List<TodoEntity> entities) {
        return entities.isEmpty() ? new ArrayList<TodoResponse>()
                : entities
                .stream()
                .map(todo -> mapper.map(todo, TodoResponse.class))
                .collect(Collectors.toList());
    }

    private Optional<TodoEntity> findTodoById(Long id) {
        return repository.findById(id);
    }
}
