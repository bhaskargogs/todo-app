package com.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTodoRequest {

    @NotBlank(message = "Description must not be Blank")
    private String description;

    private OffsetDateTime dueDate;

}
