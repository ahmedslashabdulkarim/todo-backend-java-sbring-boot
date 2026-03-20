package org.example.todobackendjavasbringboot.dto;

import org.example.todobackendjavasbringboot.model.TodoStatus;


public record TodoDto(
        String description,
        TodoStatus status
) {}
