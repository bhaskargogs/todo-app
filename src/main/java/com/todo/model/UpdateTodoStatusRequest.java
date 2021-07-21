package com.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTodoStatusRequest {

    @NotNull(message = "ID must not be null")
    private Long id;

    @NotBlank(message = "status must not be null")
    @Pattern(regexp = "DONE|NOT_DONE")
    private String status;
}
