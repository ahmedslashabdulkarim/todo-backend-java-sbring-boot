package org.example.todobackendjavasbringboot.repository;

import org.example.todobackendjavasbringboot.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryTodoRepository implements TodoRepository {

    private final Map<String, Todo> storage = new HashMap<>();

    @Override
    public List<Todo> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Todo> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Todo save(Todo todo) {
        storage.put(todo.id(), todo);
        return todo;
    }

    @Override
    public void deleteById(String id) {
        storage.remove(id);
    }
}
