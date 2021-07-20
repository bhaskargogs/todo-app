package com.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTodoDTO {

    @NotNull(message = "Description must not be null")
    private String description;

    private OffsetDateTime dueDate;

}
