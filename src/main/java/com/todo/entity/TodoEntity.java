package com.todo.entity;

import com.todo.type.TodoStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@Entity(name = "todo")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Column
    private String description;

    @Getter
    @Setter
    @Column(name = "created_date", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdDate;

    @Getter
    private TodoStatus status;

    @Getter
    @Column(name = "due_date", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dueDate;

    @Getter
    @Setter
    @Column(name = "updated_date", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedDate;


    public TodoEntity(Long id, String description, LocalDateTime createdDate,
                      TodoStatus status, LocalDateTime dueDate, LocalDateTime updatedDate) {
        this.id = id;
        this.description = description;
        this.createdDate = createdDate;
        this.status = status;
        this.dueDate = dueDate;
        this.updatedDate = updatedDate;
    }

    public TodoEntity(String description, LocalDateTime createdDate, TodoStatus status,
                      LocalDateTime dueDate, LocalDateTime updatedDate) {
        this.description = description;
        this.createdDate = createdDate;
        this.status = status;
        this.dueDate = dueDate;
        this.updatedDate = updatedDate;
    }

}
