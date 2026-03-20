package org.example.todobackendjavasbringboot.model;


public record Todo(
        String id,
        String description,
        TodoStatus status
) {}

