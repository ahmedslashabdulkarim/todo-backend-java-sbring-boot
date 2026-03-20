package org.example.todobackendjavasbringboot.repository;

import org.example.todobackendjavasbringboot.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {

    List<Todo> findAll();

    Optional<Todo> findById(String id);

    Todo save(Todo todo);

    void deleteById(String id);
}
