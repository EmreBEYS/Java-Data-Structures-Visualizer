package com.emrebeys.datastructures.graph;

import java.util.Objects;

/**
 * Graph içerisindeki bir düğümü temsil eder.
 *
 * @param <T> Düğümde tutulacak veri tipi
 */

public class Vertex<T>  {
    private final T value;

    public Vertex(T value){
        this.value=value;
    }

    public T getValue(){
        return value;
    }
    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }

        if (!(object instanceof Vertex<?> other)) {
            return false;
        }

        return Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
