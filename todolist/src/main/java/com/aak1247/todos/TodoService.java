package com.aak1247.todos;

import com.aak1247.common.AbstractDBService;
import com.aak1247.todos.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TodoService {
    private final AbstractDBService dbService;

    @Autowired
    public TodoService(AbstractDBService dbService) {
        this.dbService = dbService;
    }

    Mono<Todo> getTodoById(String key) {
        return Mono.create(todoMonoSink -> todoMonoSink.success((Todo) dbService.find(key)));
    }

    Mono<Todo> createTodo(final Todo todo) {
        final Todo todoCopy = todo.copy();
        if (todoCopy.getId() == -1) {
            todoCopy.setId(Math.toIntExact(dbService.size()));
        }
        var res = dbService.insert(String.valueOf(todoCopy.getId()), todoCopy);
        if (res) {
            return Mono.create(todoMonoSink -> todoMonoSink.success(todoCopy));
        } else {
            return Mono.empty();
        }
    }

    Mono<Boolean> removeTodo(String id) {
        return Mono.create(booleanMonoSink -> booleanMonoSink.success(dbService.remove(id)));
    }


    Flux<Todo> getAllTodo() {
        return Flux.fromStream(dbService.findAll()
                .stream()
                .filter(o -> o instanceof Todo)
                .map(o -> (Todo) o));
    }
}
