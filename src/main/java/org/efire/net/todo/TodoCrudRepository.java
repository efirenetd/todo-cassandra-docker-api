package org.efire.net.todo;

import com.datastax.oss.driver.shaded.guava.common.base.Functions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class TodoCrudRepository implements CrudRepository<Todo, UUID> {

    private Map<UUID, Todo> todoStore = new ConcurrentHashMap<>();

    @Override
    public Todo save(Todo todo) {
        return todoStore.put(todo.getId(), todo);
    }

    @Override
    public <S extends Todo> Iterable<S> saveAll(Iterable<S> iterable) {
        todoStore.putAll(StreamSupport
                .stream(iterable.spliterator(), true)
                .collect(Collectors.toMap(Todo::getId, Functions.identity())));

        // ToDo IO: return correct data
        return null;
    }

    @Override
    public Optional<Todo> findById(UUID uuid) {
        return Optional.ofNullable(todoStore.get(uuid));
    }

    @Override
    public boolean existsById(UUID uuid) {
        return todoStore.containsKey(uuid);
    }

    @Override
    public Iterable<Todo> findAll() {
        return todoStore.values();
    }

    @Override
    public Iterable<Todo> findAllById(Iterable<UUID> uuids) {
        return null;
    }

    @Override
    public long count() {
        return todoStore.size();
    }

    @Override
    public void deleteById(UUID uuid) {
        todoStore.remove(uuid);
    }

    @Override
    public void delete(Todo entity) {
        todoStore.remove(entity.getId());
    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(Iterable<? extends Todo> entities) {

    }

    @Override
    public void deleteAll() {
        todoStore.clear();
    }
}
