package TP2;

import java.util.*;

class BellmanFord {
    private final double[] dist;  // Distâncias mínimas do vértice inicial
    private final int[] pred;     // Predecessores no caminho mais curto
    private final Digraph graph;  // Grafo ponderado
    private boolean hasNegativeCycle; // Indica se existe ciclo negativo

    public BellmanFord(Digraph graph) {
        this.graph = graph;
        int V = graph.getV();
        dist = new double[V];
        pred = new int[V];
        hasNegativeCycle = false;
    }

    public boolean findShortestPaths(int source) {
        int V = graph.getV();
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        Arrays.fill(pred, -1); // NIL
        dist[source] = 0.0;

        // Relaxa todas as arestas V-1 vezes
        for (int i = 1; i < V; i++) {
            for (Edge edge : graph.edges()) {
                int u = edge.v;
                int v = edge.w;
                double weight = edge.weight;
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pred[v] = u;
                }
            }
        }

        // Verifica ciclos negativos
        for (Edge edge : graph.edges()) {
            int u = edge.v;
            int v = edge.w;
            double weight = edge.weight;
            if (dist[u] + weight < dist[v]) {
                hasNegativeCycle = true;
                return false; // Encontrado ciclo negativo
            }
        }
        return true; // Sem ciclos negativos
    }

    // Retorna a distância mínima até um vértice
    public double getDistance(int v) {
        return dist[v];
    }

    // Retorna o caminho mais curto até um vértice como uma lista de vértices
    public List<Integer> getPath(int target) {
        List<Integer> path = new ArrayList<>();
        if (dist[target] == Double.POSITIVE_INFINITY || hasNegativeCycle) {
            return path; // Sem caminho ou ciclo negativo detectado
        }
        for (int at = target; at != -1; at = pred[at]) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    // Verifica se há ciclos negativos
    public boolean hasNegativeCycle() {
        return hasNegativeCycle;
    }
}