package com.todo.service;

import com.todo.entity.TodoEntity;
import com.todo.exception.InvalidConstraintException;
import com.todo.exception.NotFoundException;
import com.todo.model.CreateTodoRequest;
import com.todo.model.TodoResponse;
import com.todo.model.UpdateTodoRequest;
import com.todo.model.UpdateTodoStatusRequest;
import com.todo.repository.TodoRepository;
import com.todo.type.TodoStatus;
import com.todo.util.TodoUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.lang.String.valueOf;

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
        return mapper.map(TodoUtils.findTodoById(repository, id)
                .orElseThrow(() -> new NotFoundException("id", valueOf(id))), TodoResponse.class);
    }

    public List<TodoResponse> findAllTodos() {
        return TodoUtils.findList(mapper, repository.findAll());
    }

    public List<TodoResponse> findByStatus(TodoStatus status) {
        return TodoUtils.findList(mapper, repository.findByStatus(status));
    }

    public TodoResponse update(Long id, UpdateTodoRequest updateTodoRequest) {
        TodoEntity entityToUpdate = repository.getById(id);
        TodoResponse updatedResponse;
        try {
            if (TodoStatus.PAST_DUE == entityToUpdate.getStatus()) {
                throw new InvalidConstraintException("status", valueOf(entityToUpdate.getStatus()));
            }
            entityToUpdate = new TodoEntity(id, updateTodoRequest.getDescription(), entityToUpdate.getCreatedDate(),
                    TodoStatus.valueOf(TodoStatus.class, updateTodoRequest.getStatus()), updateTodoRequest.getDueDate(), LocalDateTime.now());


            entityToUpdate = repository.save(entityToUpdate);
            updatedResponse = mapper.map(entityToUpdate, TodoResponse.class);
        } catch (InvalidConstraintException e) {
            throw new InvalidConstraintException("TodoService.update(): Invalid Constraints - " + e.getMessage());
        }
        return updatedResponse;
    }

    public void delete(Long id) {
        repository.delete(TodoUtils.findTodoById(repository, id).orElseThrow(() -> new NotFoundException("id", valueOf(id))));
    }

}
