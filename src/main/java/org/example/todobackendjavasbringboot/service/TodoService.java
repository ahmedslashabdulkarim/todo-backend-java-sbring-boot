package org.example.todobackendjavasbringboot.service;

import lombok.RequiredArgsConstructor;
import org.example.todobackendjavasbringboot.dto.TodoDto;
import org.example.todobackendjavasbringboot.model.Todo;
import org.example.todobackendjavasbringboot.model.TodoStatus;
import org.example.todobackendjavasbringboot.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo findById(String id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Todo with id " + id + " not found"));
    }

    public Todo create(TodoDto dto) {
        String id = UUID.randomUUID().toString();
        TodoStatus status = dto.status() != null ? dto.status() : TodoStatus.TODO;
        Todo todo = new Todo(id, dto.description(), status);
        return todoRepository.save(todo);
    }

    public Todo update(String id, TodoDto dto) {
        Todo existing = findById(id);
        Todo updated = new Todo(
                existing.id(),
                dto.description() != null ? dto.description() : existing.description(),
                dto.status() != null ? dto.status() : existing.status()
        );
        return todoRepository.save(updated);
    }

    public void delete(String id) {
        findById(id); // wirft Fehler, falls nicht vorhanden
        todoRepository.deleteById(id);
    }
}

