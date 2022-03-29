package com.lspro.todoList.api.controllers;

import com.lspro.todoList.entities.Todo;
import com.lspro.todoList.entities.TodoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoRepository todoRepository;

// Public constructor
    public TodoController(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }
// Get all todos
    @GetMapping
    public List<Todo> getTodos(){
        return todoRepository.findAll();
    }
// Get todo by Id
    @GetMapping("/{todoId}")
    public Optional<Todo> getTodo(@PathVariable("todoId") Long todoId){
        var todo = todoRepository.findById(todoId);
        return todo;
    }
// Save new todo
    @PostMapping
    public Todo newTodo(@RequestBody Todo todo ){
        return this.todoRepository.save(todo);

    }
// Edit todo
    @PutMapping("/{todoId}")
    public Optional<Todo> updateTodo(@PathVariable("todoId") Long todoId, @RequestBody Todo updatedTodo){
        return this.todoRepository.findById(todoId)
                .map(oldTodo -> this.todoRepository.save(updatedTodo));
    }
// Delete todo
    @DeleteMapping("/{todoId}")
    public void deleteTodo(@PathVariable("todoId") Long todoId){
        this.todoRepository.deleteById(todoId);
    }

}
