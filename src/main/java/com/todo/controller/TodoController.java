package com.todo.controller;

import com.todo.model.CreateTodoRequest;
import com.todo.model.TodoResponse;
import com.todo.model.UpdateTodoRequest;
import com.todo.service.TodoService;
import com.todo.type.TodoStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService service;

    @PostMapping
    public TodoResponse create(@Valid @RequestBody CreateTodoRequest createTodoRequest) {
        return service.create(createTodoRequest);
    }

    @GetMapping
    public List<TodoResponse> findAll(@Valid @RequestParam(value = "status", required = false) TodoStatus status) {
        return (status != null)
                ? service.findByStatus(status)
                : service.findAllTodos();
    }

    @GetMapping("/{id}")
    public TodoResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public TodoResponse update(@PathVariable Long id, @Valid @RequestBody UpdateTodoRequest updateTodoRequest) {
        return service.update(id, updateTodoRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
