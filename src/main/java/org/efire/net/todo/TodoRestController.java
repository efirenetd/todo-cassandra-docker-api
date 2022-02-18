package org.efire.net.todo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/v1/todos")
public class TodoRestController {

    //private TodoInMemoryService todoService;
    private TodoServiceImpl todoService;

/*    public TodoRestController(TodoInMemoryService todoService) {
        this.todoService = todoService;
    }*/

    public TodoRestController(TodoServiceImpl todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public Stream<Todo> findAll(HttpServletRequest req) {
        return null;
    }

    @GetMapping("/{uid}")
    public ResponseEntity<Todo> findById(HttpServletRequest req, @PathVariable(value = "uid") String uid) {
        return ResponseEntity.ok(todoService.findById(uid));
    }

    @PostMapping
    public ResponseEntity<Todo> create(HttpServletRequest req, @RequestBody Todo todo) throws URISyntaxException {
        todo.setUrl(req.getRequestURL().toString());
        var saveTodo = todoService.save(todo);
        return ResponseEntity.created(new URI(req.getRequestURL().toString())).body(saveTodo);
    }

    @PatchMapping("/{uid}")
    public ResponseEntity<Todo> update(HttpServletRequest req,
                                       @PathVariable(value = "uid") String uid,
                                       @RequestBody Todo todo) {

        return ResponseEntity.accepted().body(null);
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "uid") String uid) {
        // if not found
        //return ResponseEntity.notFound().build();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll(HttpServletRequest request) {
        // edit the structure
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
