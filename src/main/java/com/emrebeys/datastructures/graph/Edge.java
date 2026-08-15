package com.emrebeys.datastructures.graph;

/**
 * Graph içerisindeki iki vertex arasındaki bağlantıyı temsil eder.
 *
 * @param <T> Vertex veri tipi
 */
public class Edge<T> {

    private final Vertex<T> source;
    private final Vertex<T> destination;
    private final double weight;

    public Edge(Vertex<T> source, Vertex<T> destination) {
        this(source, destination, 1.0);
    }

    public Edge(Vertex<T> source, Vertex<T> destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex<T> getSource() {
        return source;
    }

    public Vertex<T> getDestination() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " -> " + destination + " (" + weight + ")";
    }
}
