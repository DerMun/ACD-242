package TP2;

import java.util.*;

public class MaxFlow {
    private final Digraph graph;

    public MaxFlow(Digraph graph) {
        this.graph = graph;
    }

    public double fordFulkerson(int source, int sink) {
        int V = graph.getV();
        double[][] residualCapacity = new double[V][V];

        for (int v = 0; v < V; v++) {
            for (Edge edge : graph.adj(v)) {
                residualCapacity[edge.v][edge.w] = edge.weight;
            }
        }

        double maxFlow = 0.0;
        int[] parent = new int[V];

        while (bfs(residualCapacity, source, sink, parent)) {
            double pathFlow = Double.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residualCapacity[u][v]);
            }

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                residualCapacity[u][v] -= pathFlow;
                residualCapacity[v][u] += pathFlow;
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    private boolean bfs(double[][] residualCapacity, int source, int sink, int[] parent) {
        boolean[] visited = new boolean[graph.getV()];
        Arrays.fill(visited, false);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v < graph.getV(); v++) {
                if (!visited[v] && residualCapacity[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;

                    if (v == sink) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}