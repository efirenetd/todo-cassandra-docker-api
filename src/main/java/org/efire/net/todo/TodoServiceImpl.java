package org.efire.net.todo;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TodoServiceImpl {

    private TodoCassandraReposity repo;

    public TodoServiceImpl(TodoCassandraReposity repo) {
        this.repo = repo;
    }


    public Todo save(Todo todo) {
        TodoEntity todoEntity = convertTodoEntity(todo);
        var savedTodo = repo.save(todoEntity);
        return convertTodoBy(savedTodo);
    }

    public void update(Todo todo) {

    }

    public Todo findById(String uid) {
        var todoEntity =  repo.findById(UUID.fromString(uid))
                .orElseThrow();
        return convertTodoBy(todoEntity);
    }

    private Todo convertTodoBy(TodoEntity existingTodo) {
        var todo = new Todo();
        todo.setTitle(existingTodo.getTitle());
        todo.setId(existingTodo.getUid());
        todo.setCompleted(existingTodo.isCompleted());
        todo.setOrder(existingTodo.getOrder());
        return todo;
    }

    private TodoEntity convertTodoEntity(Todo todo) {
        return new TodoEntity(todo.getTitle(), todo.getOrder());
    }
}
