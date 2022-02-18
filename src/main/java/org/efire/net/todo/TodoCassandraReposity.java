package org.efire.net.todo;

import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface TodoCassandraReposity extends CassandraRepository<TodoEntity, UUID> {
}
