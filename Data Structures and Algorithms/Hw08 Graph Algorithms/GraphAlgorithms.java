import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Your implementation of various graph algorithms.
 *
 * @author Sunny Patel
 * @version 1.0
 * @userid spatel725
 * @GTID 903466059
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class GraphAlgorithms {

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * NOTE: You MUST implement this method recursively, or else you will lose
     * all points for this method.
     *
     * You may import/use java.util.Set, java.util.List, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("Start cannot be null!");
        } //if
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null!");
        } //if
        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Start is not in graph!");
        } //if
        List<Vertex<T>> vertList = new ArrayList<>();
        Set<Vertex<T>> visitSet = new HashSet<>();
        dfsH(start, graph, vertList, visitSet);
        return vertList;
    } //dfs

    /**
     * Helper Method for dfs
     *
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @param vertList list of vertices in visited order
     * @param visitSet set of visited vertices
     * @param <T> data type
     */
    public static <T> void dfsH(Vertex<T> start, Graph<T> graph, List<Vertex<T>> vertList, Set<Vertex<T>> visitSet) {
        vertList.add(start);
        visitSet.add(start);
        for (VertexDistance<T> vertex : graph.getAdjList().get(start)) {
            if (!visitSet.contains(vertex.getVertex())) {
                dfsH(vertex.getVertex(), graph, vertList, visitSet);
            } //if
        } //for
    } //dfsH

    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing
     * infinity) if no path exists.
     *
     * You may import/use java.util.PriorityQueue,
     * java.util.Map, and java.util.Set and any class that
     * implements the aforementioned interfaces, as long as your use of it
     * is efficient as possible.
     *
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     *
     * 1) Check if all of the vertices have been visited.
     * 2) Check if the PQ is empty yet.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every
     * other node in the graph
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("Start cannot be null!");
        } //if
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null!");
        } //if
        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Start is not in graph!");
        } //if
        Set<Vertex<T>> visitSet = new HashSet<>();
        Map<Vertex<T>, Integer> shortDMap = new HashMap<>();
        Queue<VertexDistance<T>> pq = new PriorityQueue<>();
        for (Vertex<T> v: graph.getAdjList().keySet()) {
            if (v.equals(start)) {
                shortDMap.put(v, 0);
            } else {
                shortDMap.put(v, Integer.MAX_VALUE);
            } //else
        } //for
        pq.add(new VertexDistance<>(start, 0));
        while (!pq.isEmpty() && !(visitSet.size() == graph.getVertices().size())) {
            VertexDistance<T> u = pq.remove();
            for (VertexDistance<T> w : graph.getAdjList().get(u.getVertex())) {
                if (!visitSet.contains(w) && (w.getDistance() + u.getDistance() < shortDMap.get(w.getVertex()))) {
                    pq.add(new VertexDistance<>(w.getVertex(), w.getDistance() + u.getDistance()));
                    shortDMap.put(w.getVertex(), w.getDistance() + u.getDistance());
                } //if
            } //for
        } //while
        return shortDMap;
    } //dijkstras

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops or parallel edges in the MST.
     *
     * You may import/use java.util.PriorityQueue, java.util.Set, and any
     * class that implements the aforementioned interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * The only instance of java.util.Map that you may use is the adjacency
     * list from graph. DO NOT create new instances of Map for this method
     * (storing the adjacency list in a variable is fine).
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("Start cannot be null!");
        } //if
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null!");
        } //if
        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Start is not in graph!");
        } //if
        Set<Vertex<T>> visitSet = new HashSet<>();
        Set<Edge<T>> mst = new HashSet<>();
        Queue<Edge> pq = new PriorityQueue<>();
        for (VertexDistance<T> s : graph.getAdjList().get(start)) {
            pq.add(new Edge<>(start, s.getVertex(), s.getDistance()));
        } //for
        visitSet.add(start);
        while (!pq.isEmpty() && !(visitSet.size() == graph.getVertices().size())) {
            Edge<T> edge = pq.remove();
            if (!visitSet.contains(edge.getV())) {
                visitSet.add(edge.getV());
                mst.add(new Edge<>(edge.getU(), edge.getV(), edge.getWeight()));
                mst.add(new Edge<>(edge.getV(), edge.getU(), edge.getWeight()));
                for (VertexDistance<T> v : graph.getAdjList().get(edge.getV())) {
                    if (!visitSet.contains(v.getVertex())) {
                        pq.add(new Edge<>(edge.getV(), v.getVertex(), v.getDistance()));
                    } //if
                } //for
            } //if
        } //while
        if (!((mst.size() / 2) == graph.getVertices().size() - 1)) {
            return null;
        } else {
            return mst;
        } //else
    } //prims
} //GraphAlgorithms
