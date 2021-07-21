package com.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTodoRequest {

    @NotNull(message = "ID must not be null")
    private Long id;

    @NotBlank(message = "Description must not be null")
    private String description;

    @FutureOrPresent(message = "Due date must be future or present")
    private LocalDateTime dueDate;

    @NotBlank(message = "status must not be null")
    private String status;
}
