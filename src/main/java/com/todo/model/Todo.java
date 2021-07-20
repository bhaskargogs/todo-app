package com.todo.model;

import com.todo.type.TodoStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Table
@Entity(name = "todo")
public class Todo {

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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-YYYY HH:mm")
    private OffsetDateTime createdDate;

    @Getter
    private TodoStatus status;

    @Getter
    @Column(name = "due_date", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-YYYY HH:mm")
    private OffsetDateTime dueDate;

    @Getter
    @Setter
    @Column(name = "updated_date", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-YYYY HH:mm")
    private OffsetDateTime updatedDate;

    protected Todo(){}

    public Todo(Long id, String description, TodoStatus status, OffsetDateTime dueDate) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
    }

    public Todo(String description, OffsetDateTime createdDate, TodoStatus status, OffsetDateTime dueDate, OffsetDateTime updatedDate) {
        this.description = description;
        this.createdDate = createdDate;
        this.status = status;
        this.dueDate = dueDate;
        this.updatedDate = updatedDate;
    }
}