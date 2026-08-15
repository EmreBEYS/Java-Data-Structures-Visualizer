package com.emrebeys.datastructures.graph;

import java.util.*;

/**
 * Adjacency List tabanlı Graph implementasyonu.
 *
 * Yönlü ve yönsüz graph yapılarını destekler.
 * BFS ve DFS dolaşma algoritmalarını içerir.
 *
 * @param <T> Vertex içerisinde tutulacak veri tipi
 */
public class Graph<T> {

    private final GraphType graphType;

    /*
     * Her vertex için ona bağlı edge'leri tutan
     * adjacency list yapısı.
     */
    private final Map<Vertex<T>, List<Edge<T>>> adjacencyList;

    /**
     * Belirtilen graph türüyle yeni bir Graph oluşturur.
     *
     * @param graphType DIRECTED veya UNDIRECTED
     */
    public Graph(GraphType graphType) {

        if (graphType == null) {
            throw new IllegalArgumentException(
                    "Graph türü null olamaz."
            );
        }

        this.graphType = graphType;
        this.adjacencyList = new LinkedHashMap<>();
    }

    /**
     * Graph içerisine yeni bir vertex ekler.
     *
     * Aynı değere sahip vertex zaten varsa tekrar eklenmez.
     *
     * @param value Vertex içerisinde tutulacak değer
     * @return Vertex eklendiyse true, zaten varsa false
     */
    public boolean addVertex(T value) {

        Vertex<T> vertex = new Vertex<>(value);

        if (adjacencyList.containsKey(vertex)) {
            return false;
        }

        adjacencyList.put(vertex, new ArrayList<>());

        return true;
    }

    /**
     * Graph içerisinden vertex siler.
     *
     * Vertex silindiğinde bu vertex ile bağlantılı
     * bütün edge'ler de kaldırılır.
     *
     * @param value Silinecek vertex değeri
     * @return Vertex bulundu ve silindiyse true
     */
    public boolean removeVertex(T value) {

        Vertex<T> vertex = findVertex(value);

        if (vertex == null) {
            return false;
        }

        // Vertex'in kendi adjacency listesini kaldırıyoruz.
        adjacencyList.remove(vertex);

        /*
         * Diğer vertex'lerin adjacency listelerinde
         * silinen vertex'e giden edge varsa onları da kaldırıyoruz.
         */
        for (List<Edge<T>> edges : adjacencyList.values()) {

            edges.removeIf(
                    edge -> edge.getDestination().equals(vertex)
            );
        }

        return true;
    }

    /**
     * İki vertex arasına varsayılan ağırlığı 1.0 olan edge ekler.
     */
    public boolean addEdge(T source, T destination) {
        return addEdge(source, destination, 1.0);
    }

    /**
     * İki vertex arasına ağırlıklı edge ekler.
     *
     * Vertex'lerden biri mevcut değilse edge eklenmez.
     *
     * UNDIRECTED graph durumunda ters yönlü edge de
     * otomatik olarak oluşturulur.
     *
     * @param source Başlangıç vertex'i
     * @param destination Hedef vertex
     * @param weight Edge ağırlığı
     * @return Edge başarıyla eklendiyse true
     */
    public boolean addEdge(
            T source,
            T destination,
            double weight
    ) {

        Vertex<T> sourceVertex = findVertex(source);
        Vertex<T> destinationVertex = findVertex(destination);

        if (sourceVertex == null || destinationVertex == null) {
            return false;
        }

        // Aynı edge'in tekrar eklenmesini engelliyoruz.
        if (containsEdge(source, destination)) {
            return false;
        }

        Edge<T> edge = new Edge<>(
                sourceVertex,
                destinationVertex,
                weight
        );

        adjacencyList
                .get(sourceVertex)
                .add(edge);

        /*
         * Graph yönsüzse ters bağlantıyı da oluşturuyoruz.
         *
         * Self-loop durumunda aynı edge'i iki kez eklememek
         * için source ve destination kontrolü yapıyoruz.
         */
        if (graphType == GraphType.UNDIRECTED
                && !sourceVertex.equals(destinationVertex)) {

            Edge<T> reverseEdge = new Edge<>(
                    destinationVertex,
                    sourceVertex,
                    weight
            );

            adjacencyList
                    .get(destinationVertex)
                    .add(reverseEdge);
        }

        return true;
    }

    /**
     * İki vertex arasındaki edge'i kaldırır.
     *
     * UNDIRECTED graph yapısında ters edge de kaldırılır.
     */
    public boolean removeEdge(
            T source,
            T destination
    ) {

        Vertex<T> sourceVertex = findVertex(source);
        Vertex<T> destinationVertex = findVertex(destination);

        if (sourceVertex == null || destinationVertex == null) {
            return false;
        }

        boolean removed = adjacencyList
                .get(sourceVertex)
                .removeIf(
                        edge -> edge
                                .getDestination()
                                .equals(destinationVertex)
                );

        if (!removed) {
            return false;
        }

        /*
         * Yönsüz graph içerisinde aynı bağlantının
         * ters yönünü de siliyoruz.
         */
        if (graphType == GraphType.UNDIRECTED
                && !sourceVertex.equals(destinationVertex)) {

            adjacencyList
                    .get(destinationVertex)
                    .removeIf(
                            edge -> edge
                                    .getDestination()
                                    .equals(sourceVertex)
                    );
        }

        return true;
    }

    /**
     * Graph içerisinde belirtilen vertex'in
     * bulunup bulunmadığını kontrol eder.
     */
    public boolean containsVertex(T value) {
        return findVertex(value) != null;
    }

    /**
     * İki vertex arasında edge olup olmadığını kontrol eder.
     */
    public boolean containsEdge(
            T source,
            T destination
    ) {

        Vertex<T> sourceVertex = findVertex(source);
        Vertex<T> destinationVertex = findVertex(destination);

        if (sourceVertex == null || destinationVertex == null) {
            return false;
        }

        for (Edge<T> edge : adjacencyList.get(sourceVertex)) {

            if (edge.getDestination().equals(destinationVertex)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Belirtilen edge'in ağırlığını döndürür.
     *
     * Edge bulunamazsa null döner.
     */
    public Double getEdgeWeight(
            T source,
            T destination
    ) {

        Vertex<T> sourceVertex = findVertex(source);
        Vertex<T> destinationVertex = findVertex(destination);

        if (sourceVertex == null || destinationVertex == null) {
            return null;
        }

        for (Edge<T> edge : adjacencyList.get(sourceVertex)) {

            if (edge.getDestination().equals(destinationVertex)) {
                return edge.getWeight();
            }
        }

        return null;
    }

    /**
     * Belirtilen vertex'in doğrudan komşularını döndürür.
     *
     * Vertex bulunamazsa boş liste döner.
     */
    public List<T> getNeighbors(T value) {

        Vertex<T> vertex = findVertex(value);

        if (vertex == null) {
            return Collections.emptyList();
        }

        List<T> neighbors = new ArrayList<>();

        for (Edge<T> edge : adjacencyList.get(vertex)) {

            neighbors.add(
                    edge.getDestination().getValue()
            );
        }

        return neighbors;
    }

    /**
     * Breadth First Search (BFS) algoritması.
     *
     * Başlangıç vertex'inden başlayarak graph'ı
     * seviye seviye dolaşır.
     *
     * @param start Başlangıç vertex değeri
     * @return BFS ziyaret sırası
     */
    public List<T> bfs(T start) {

        Vertex<T> startVertex = findVertex(start);

        if (startVertex == null) {
            return Collections.emptyList();
        }

        List<T> traversal = new ArrayList<>();
        Set<Vertex<T>> visited = new HashSet<>();
        Queue<Vertex<T>> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.offer(startVertex);

        while (!queue.isEmpty()) {

            Vertex<T> current = queue.poll();

            traversal.add(current.getValue());

            for (Edge<T> edge : adjacencyList.get(current)) {

                Vertex<T> neighbor = edge.getDestination();

                if (!visited.contains(neighbor)) {

                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        return traversal;
    }

    /**
     * Depth First Search (DFS) algoritması.
     *
     * Başlangıç vertex'inden başlayarak graph'ın
     * mümkün olduğunca derinine ilerler.
     *
     * @param start Başlangıç vertex değeri
     * @return DFS ziyaret sırası
     */
    public List<T> dfs(T start) {

        Vertex<T> startVertex = findVertex(start);

        if (startVertex == null) {
            return Collections.emptyList();
        }

        List<T> traversal = new ArrayList<>();
        Set<Vertex<T>> visited = new HashSet<>();

        dfsRecursive(
                startVertex,
                visited,
                traversal
        );

        return traversal;
    }

    /**
     * DFS algoritmasının recursive yardımcı metodu.
     */
    private void dfsRecursive(
            Vertex<T> vertex,
            Set<Vertex<T>> visited,
            List<T> traversal
    ) {

        visited.add(vertex);

        traversal.add(vertex.getValue());

        for (Edge<T> edge : adjacencyList.get(vertex)) {

            Vertex<T> neighbor = edge.getDestination();

            if (!visited.contains(neighbor)) {

                dfsRecursive(
                        neighbor,
                        visited,
                        traversal
                );
            }
        }
    }

    /**
     * Graph içerisindeki vertex sayısını döndürür.
     */
    public int vertexCount() {
        return adjacencyList.size();
    }

    /**
     * Graph içerisindeki toplam edge sayısını döndürür.
     *
     * UNDIRECTED graph yapısında fiziksel olarak iki edge
     * tutulduğu için sonuç ikiye bölünür.
     *
     * Self-loop ise yalnızca bir kez sayılır.
     */
    public int edgeCount() {

        int count = 0;
        int selfLoops = 0;

        for (Map.Entry<Vertex<T>, List<Edge<T>>> entry
                : adjacencyList.entrySet()) {

            Vertex<T> source = entry.getKey();

            for (Edge<T> edge : entry.getValue()) {

                count++;

                if (source.equals(edge.getDestination())) {
                    selfLoops++;
                }
            }
        }

        if (graphType == GraphType.DIRECTED) {
            return count;
        }

        /*
         * Normal yönsüz edge'ler iki kere tutulur.
         * Self-loop ise yalnızca bir kere tutulur.
         */
        return ((count - selfLoops) / 2) + selfLoops;
    }

    /**
     * Graph boşsa true döndürür.
     */
    public boolean isEmpty() {
        return adjacencyList.isEmpty();
    }

    /**
     * Graph içerisindeki bütün vertex ve edge'leri temizler.
     */
    public void clear() {
        adjacencyList.clear();
    }

    /**
     * Graph türünü döndürür.
     */
    public GraphType getGraphType() {
        return graphType;
    }

    /**
     * Verilen değere karşılık gelen Vertex nesnesini bulur.
     */
    private Vertex<T> findVertex(T value) {

        Vertex<T> searchVertex = new Vertex<>(value);

        for (Vertex<T> vertex : adjacencyList.keySet()) {

            if (vertex.equals(searchVertex)) {
                return vertex;
            }
        }

        return null;
    }

    /**
     * Graph'ın adjacency list görünümünü
     * okunabilir biçimde döndürür.
     */
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        for (Map.Entry<Vertex<T>, List<Edge<T>>> entry
                : adjacencyList.entrySet()) {

            builder
                    .append(entry.getKey())
                    .append(" -> ");

            List<Edge<T>> edges = entry.getValue();

            if (edges.isEmpty()) {

                builder.append("[]");

            } else {

                builder.append("[");

                for (int i = 0; i < edges.size(); i++) {

                    Edge<T> edge = edges.get(i);

                    builder
                            .append(edge.getDestination())
                            .append("(")
                            .append(edge.getWeight())
                            .append(")");

                    if (i < edges.size() - 1) {
                        builder.append(", ");
                    }
                }

                builder.append("]");
            }

            builder.append(System.lineSeparator());
        }

        return builder.toString();
    }
}