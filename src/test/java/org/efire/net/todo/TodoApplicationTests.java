package org.efire.net.todo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TodoApplicationTests {

	@Autowired
	private TodoCassandraReposity todoCassandraReposity;

	@Test
	void contextLoads() {
	}

	@Test
	void shouldSaveTask() {
		var todo = new TodoEntity("Sample Task", 1);
		todoCassandraReposity.save(todo);
		assertTrue(todoCassandraReposity.existsById(todo.getUid()));
	}
}
