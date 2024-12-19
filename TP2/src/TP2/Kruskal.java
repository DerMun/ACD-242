package TP2;

import java.util.*;

public class Kruskal {
    public static double findMST(Graph graph) {
        List<Edge> edges = new ArrayList<>(graph.edges());
        Collections.sort(edges, Comparator.comparingDouble(e -> e.weight));

        //estruturas para a união e busca de conjuntos disjuntos
        int[] parent = new int[graph.V()];
        int[] rank = new int[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        double mstWeight = 0;
        List<Edge> mstEdges = new ArrayList<>();

        for (Edge edge : edges) {
            int rootU = find(parent, edge.v);
            int rootV = find(parent, edge.w);

            if (rootU != rootV) { // Não forma ciclo
                mstEdges.add(edge);
                mstWeight += edge.weight;
                union(parent, rank, rootU, rootV);
            }
        }

        System.out.println("Kruskal's MST:");
        for (Edge e : mstEdges) {
            System.out.println(e.v + " - " + e.w + " (weight: " + e.weight + ")");
        }

        return mstWeight;
    }

    private static int find(int[] parent, int v) {
        if (parent[v] != v) {
            parent[v] = find(parent, parent[v]); // Compressão de caminho
        }
        return parent[v];
    }

    private static void union(int[] parent, int[] rank, int rootU, int rootV) {
        if (rank[rootU] > rank[rootV]) {
            parent[rootV] = rootU;
        } else if (rank[rootU] < rank[rootV]) {
            parent[rootU] = rootV;
        } else {
            parent[rootV] = rootU;
            rank[rootU]++;
        }
    }
}