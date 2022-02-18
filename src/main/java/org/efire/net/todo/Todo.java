package org.efire.net.todo;

import lombok.Data;

import java.util.UUID;

@Data
public class Todo {
    public String url;
    private UUID id;
    private String title;
    private boolean completed = false;
    private int order = 0;
}
