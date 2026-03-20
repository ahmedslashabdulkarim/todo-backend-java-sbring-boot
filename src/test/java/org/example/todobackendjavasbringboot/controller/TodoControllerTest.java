package org.example.todobackendjavasbringboot.controller;

import org.example.todobackendjavasbringboot.dto.TodoDto;
import org.example.todobackendjavasbringboot.model.Todo;
import org.example.todobackendjavasbringboot.model.TodoStatus;
import org.example.todobackendjavasbringboot.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoControllerTest {

    @Mock
    TodoService todoService;

    @InjectMocks
    TodoController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // GET ALL
    @Test
    void getAllTodos_returnsList() {
        List<Todo> todos = List.of(
                new Todo("1", "Test 1", TodoStatus.TODO),
                new Todo("2", "Test 2", TodoStatus.IN_PROGRESS)
        );

        when(todoService.findAll()).thenReturn(todos);

        var result = controller.getAllTodos();

        assertEquals(2, result.size());
        assertEquals("1", result.get(0).id());
    }

    // GET BY ID
    @Test
    void getTodoById_returnsTodo() {
        Todo todo = new Todo("1", "Test", TodoStatus.TODO);

        when(todoService.findById("1")).thenReturn(todo);

        var result = controller.getTodoById("1");

        assertEquals("1", result.id());
        assertEquals("Test", result.description());
    }

    // POST
    @Test
    void createTodo_returnsCreatedTodo() {
        TodoDto dto = new TodoDto("New Todo", TodoStatus.TODO);
        Todo created = new Todo("123", "New Todo", TodoStatus.TODO);

        when(todoService.create(dto)).thenReturn(created);

        var result = controller.createTodo(dto);

        assertEquals("123", result.id());
        assertEquals("New Todo", result.description());
    }

    // PUT
    @Test
    void updateTodo_returnsUpdatedTodo() {
        TodoDto dto = new TodoDto("Updated", TodoStatus.DONE);
        Todo updated = new Todo("1", "Updated", TodoStatus.DONE);

        when(todoService.update("1", dto)).thenReturn(updated);

        var result = controller.updateTodo("1", dto);

        assertEquals("Updated", result.description());
        assertEquals(TodoStatus.DONE, result.status());
    }

    // DELETE
    @Test
    void deleteTodo_callsService() {
        controller.deleteTodo("1");

        verify(todoService, times(1)).delete("1");
    }
}
