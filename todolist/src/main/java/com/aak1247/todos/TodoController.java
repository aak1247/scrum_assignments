package com.aak1247.todos;

import com.aak1247.todos.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/api/tasks")
    public Flux<Todo> getTodoList() {
        return todoService.getAllTodo();
    }

    @PostMapping("/api/tasks")
    public Mono<Todo> createTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    @GetMapping("/api/tasks/{id}")
    public Mono<Todo> getTodo(@PathVariable String id) {
        return todoService.getTodoById(id);
    }

    @DeleteMapping("/api/tasks/{id}")
    public Mono<Boolean> deleteTodo(@PathVariable String id) {
        return todoService.removeTodo(id);
    }
}
