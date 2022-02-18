package org.efire.net.todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "todos")
public class TodoEntity {
    @PrimaryKey
    @Column("uid")
    @CassandraType(type = Name.UUID)
    private UUID uid;

    @Column("title")
    @CassandraType(type = Name.TEXT)
    private String title;

    @Column("completed")
    @CassandraType(type = Name.BOOLEAN)
    private boolean completed = false;

    @Column("offset")
    @CassandraType(type = Name.INT)
    private int order = 0;

    public TodoEntity(String title, int offset) {
        this(UUID.randomUUID(), title, false, offset);
    }

}
