package org.example.todobackendjavasbringboot.controller;

import lombok.RequiredArgsConstructor;
import org.example.todobackendjavasbringboot.dto.TodoDto;
import org.example.todobackendjavasbringboot.model.Todo;
import org.example.todobackendjavasbringboot.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // Hinweis 1: GET /api/todo
    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.findAll();
    }

    // Hinweis 2: POST /api/todo
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo createTodo(@RequestBody TodoDto dto) {
        return todoService.create(dto);
    }

    // Hinweis 3: GET /api/todo/{id}
    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable String id) {
        return todoService.findById(id);
    }

    // Hinweis 4: PUT /api/todo/{id}
    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable String id, @RequestBody TodoDto dto) {
        return todoService.update(id, dto);
    }

    // Hinweis 5: DELETE /api/todo/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable String id) {
        todoService.delete(id);
    }
}
