package com.search;

import java.util.Map;
import java.util.Objects;


public class Document {

    private int id;

    private Map<String, String> properties;

    public Document(int id, Map<String, String> properties) {
        this.id = id;
        this.properties = Objects.requireNonNull(properties);
    }

    public int getId() {
        return id;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return "com.search.Document{" +
            "id=" + id +
            ", properties=" + properties +
            '}';
    }
}
