package com.todo.service;

import com.todo.entity.TodoEntity;
import com.todo.exception.InvalidConstraintException;
import com.todo.model.CreateTodoRequest;
import com.todo.model.TodoResponse;
import com.todo.repository.TodoRepository;
import com.todo.type.TodoStatus;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository repository;
    private final ModelMapper mapper;

    public TodoResponse create(CreateTodoRequest createTodoRequest) {
        TodoResponse response;
        try {
            TodoEntity entity = new TodoEntity(createTodoRequest.getDescription(), OffsetDateTime.now(), TodoStatus.NOT_DONE,
                    createTodoRequest.getDueDate(), OffsetDateTime.now());
            entity = repository.save(entity);
            response = mapper.map(entity, TodoResponse.class);
            response.setId(entity.getId());
        } catch (InvalidConstraintException e) {
            throw new InvalidConstraintException();
        }
        return response;
    }
}
