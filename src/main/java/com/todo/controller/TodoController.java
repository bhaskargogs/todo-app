package com.todo.controller;

import com.todo.model.CreateTodoRequest;
import com.todo.model.TodoResponse;
import com.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService service;

    @PostMapping
    public TodoResponse create(@Valid @RequestBody CreateTodoRequest createTodoRequest) {
        return service.create(createTodoRequest);
    }
}
