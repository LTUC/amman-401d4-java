package com.company;

import java.util.Objects;

public class Vertex {

    String data;

    public Vertex(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "label='" + data + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex vertex)) return false;
        return data.equals(vertex.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
