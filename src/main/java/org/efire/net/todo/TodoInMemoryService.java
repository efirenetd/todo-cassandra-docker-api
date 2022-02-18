package org.efire.net.todo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class TodoInMemoryService {

    private CrudRepository<Todo, UUID> crudRepository;

    public TodoInMemoryService(CrudRepository<Todo, UUID> crudRepository) {
        this.crudRepository = crudRepository;
    }

    public Stream<Todo> findAll(HttpServletRequest req) {
        return StreamSupport.stream(crudRepository.findAll().spliterator(), true);
    }

    public Todo findById(String uid) {
        return crudRepository.findById(UUID.fromString(uid)).get();
    }


    public Todo save(Todo todo) {
        todo.setId(UUID.randomUUID());
        return crudRepository.save(todo);
    }

    public Todo update(Todo todo) {
        var todoOptional = crudRepository.findById(todo.getId());
        if (todoOptional.isPresent()) {
            var existingTodo = todoOptional.get();
            if (null != existingTodo.getTitle()) {
                existingTodo.setTitle(todo.getTitle());
            }
            if (existingTodo.getOrder() != todo.getOrder()) {
                existingTodo.setOrder(todo.getOrder());
            }
            if (existingTodo.isCompleted() != todo.isCompleted()) {
                existingTodo.setCompleted(todo.isCompleted());
            }
            return  existingTodo;
        } else {
            throw new IllegalStateException("No Record found");
        }
    }
}
