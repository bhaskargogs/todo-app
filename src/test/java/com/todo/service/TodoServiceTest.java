package com.todo.service;


import com.todo.entity.TodoEntity;
import com.todo.exception.InvalidConstraintException;
import com.todo.exception.NotFoundException;
import com.todo.model.CreateTodoRequest;
import com.todo.model.TodoResponse;
import com.todo.model.UpdateTodoRequest;
import com.todo.repository.TodoRepository;
import com.todo.type.TodoStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.todo.type.TodoStatus.NOT_DONE;
import static java.lang.String.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private TodoService todoService;

    @Test
    public void getTodo_ReturnsTodoDetails() {
        TodoEntity todoEntity = new TodoEntity(1L, "Hello", LocalDateTime.now(),
                NOT_DONE, LocalDateTime.of(2021, 9, 20, 12, 30), LocalDateTime.now());

        TodoResponse todoResponse = new TodoResponse(1L, "Hello",
                "NOT_DONE", LocalDateTime.of(2021, 9, 20, 12, 30), LocalDateTime.now());

        given(todoRepository.findById(1L)).willReturn(Optional.of(todoEntity));
        given(mapper.map(todoEntity, TodoResponse.class)).willReturn(todoResponse);
        TodoResponse response = todoService.findById(1L);

        assertThat(response.getDescription()).isEqualTo("Hello");
        assertThat(response.getStatus()).isEqualTo(valueOf(NOT_DONE));
    }

    @Test
    public void getTodo_NotFound() {
        given(todoRepository.findById(1L)).willThrow(new NotFoundException("id", "1"));

        assertThrows(NotFoundException.class, () -> todoService.findById(1L));
    }

    @Test
    public void createTodo_ReturnsTodoResponse() {
        TodoEntity todoEntity = new TodoEntity(1L, "Hello", LocalDateTime.now(),
                NOT_DONE, LocalDateTime.of(2021, 9, 20, 12, 30), LocalDateTime.now());
        TodoResponse todoResponse = new TodoResponse(1L, "Hello",
                "NOT_DONE", LocalDateTime.of(2021, 9, 20, 12, 30), LocalDateTime.now());

        CreateTodoRequest createTodoRequest = new CreateTodoRequest("Hello", LocalDateTime.of(2021, 9, 20, 12, 30));

        given(todoRepository.save(any(TodoEntity.class))).willReturn(todoEntity);
        given(mapper.map(todoEntity, TodoResponse.class)).willReturn(todoResponse);

        TodoResponse response = todoService.create(createTodoRequest);
        assertThat(response).isEqualTo(todoResponse);
    }

    @Test
    public void createTodo_InvalidConstraint() {
        CreateTodoRequest createTodoRequest = new CreateTodoRequest("Hello",
                LocalDateTime.of(2021, 5, 20, 12, 30));

        given(todoRepository.save(any(TodoEntity.class))).willThrow(new InvalidConstraintException("dueDate", "20-05-2021 12:30"));
        assertThrows(InvalidConstraintException.class, () -> todoService.create(createTodoRequest));
    }

    @Test
    public void deleteTodo_deletesManufacturer() {
        TodoEntity todoEntity = new TodoEntity(1L, "Hello", LocalDateTime.now(),
                NOT_DONE, LocalDateTime.of(2021, 9, 20, 12, 30), LocalDateTime.now());
        given(todoRepository.findById(1L)).willReturn(Optional.of(todoEntity));
        todoService.delete(1L);

        verify(todoRepository, times(1)).findById(1L);
    }

    @Test
    public void deleteTodo_NotFound() {
        given(todoRepository.findById(anyLong())).willThrow(new NotFoundException("id", "1"));
        assertThrows(NotFoundException.class, () -> todoService.delete(1L));
    }

    @Test
    public void findAllManufacturers_ReturnsTodos() {
        TodoResponse todoResponse1 = new TodoResponse(1L, "Hello",
                "NOT_DONE", LocalDateTime.of(2021, 9, 20, 12, 30), LocalDateTime.now());
        TodoResponse todoResponse2 = new TodoResponse(2L, "Hello new Description",
                "NOT_DONE", LocalDateTime.of(2021, 8, 10, 9, 0), LocalDateTime.now());
        TodoEntity todoEntity1 = new TodoEntity(1L, "Hello", LocalDateTime.now(),
                NOT_DONE, LocalDateTime.of(2021, 9, 20, 12, 30), LocalDateTime.now());
        TodoEntity todoEntity2 = new TodoEntity(2L, "Hello", LocalDateTime.now(),
                NOT_DONE, LocalDateTime.of(2021, 8, 10, 9, 0), LocalDateTime.now());

        List<TodoResponse> todoList = Arrays.asList(todoResponse1, todoResponse2);
        List<TodoEntity> todoEntities = Arrays.asList(todoEntity1, todoEntity2);

        given(todoRepository.findAll()).willReturn(todoEntities);
        given(mapper.map(todoEntity1, TodoResponse.class)).willReturn(todoResponse1);
        given(mapper.map(todoEntity2, TodoResponse.class)).willReturn(todoResponse2);
        assertThat(todoService.findAllTodos()).isEqualTo(todoList);

    }

    @Test
    public void findAllManufacturers_EmptyList() {
        given(todoRepository.findAll()).willReturn(Collections.emptyList());

        assertThat(todoService.findAllTodos()).isEmpty();
    }

}
