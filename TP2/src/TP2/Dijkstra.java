package TP2;

import java.util.*;

class Dijkstra {
    private final double[] dist;    // Distâncias mínimas do vértice inicial
    private final int[] pred;       // Predecessores no caminho mais curto
    private final PriorityQueue<Vertex> pq; // Fila de prioridade
    private final Digraph graph;    // Grafo ponderado

    public Dijkstra(Digraph graph) {
        this.graph = graph;
        int V = graph.getV();
        dist = new double[V];
        pred = new int[V];
        pq = new PriorityQueue<>(Comparator.comparingDouble(v -> v.distance));
    }

    public void findShortestPaths(int source) {
        int V = graph.getV();

        // Inicialização
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        Arrays.fill(pred, -1); // NIL
        dist[source] = 0.0;

        pq.add(new Vertex(source, 0.0));

        while (!pq.isEmpty()) {
            Vertex current = pq.poll();
            int u = current.id;

            // Para cada aresta (u -> v)
            for (Edge edge : graph.adj(u)) {
                int v = edge.w;
                double weight = edge.weight;

                // Relaxamento
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pred[v] = u;

                    // Atualiza ou insere v na fila
                    pq.add(new Vertex(v, dist[v]));
                }
            }
        }
    }

    // Retorna a distância mínima até um vértice
    public double getDistance(int v) {
        return dist[v];
    }

    // Retorna o caminho mais curto até um vértice como uma lista de vértices
    public List<Integer> getPath(int target) {
        List<Integer> path = new ArrayList<>();
        if (dist[target] == Double.POSITIVE_INFINITY) {
            return path; // Sem caminho para o alvo
        }
        for (int at = target; at != -1; at = pred[at]) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    // Classe auxiliar para a fila de prioridade
    private static class Vertex {
        int id;
        double distance;

        Vertex(int id, double distance) {
            this.id = id;
            this.distance = distance;
        }
    }
}