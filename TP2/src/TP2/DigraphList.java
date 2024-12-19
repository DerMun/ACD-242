package TP2;

import java.util.ArrayList;
import java.util.List;

public class DigraphList implements Digraph{
    private final int V;               //n de vértices
    private final List<List<Edge>> adj;//lista de adjacência

    public DigraphList(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int v, int w, double weight) {
        Edge edge = new Edge(v, w, weight);
        adj.get(v).add(edge); // Adiciona a aresta na lista de adjacência do vértice v
    }

    // Retorna as arestas que saem de um vértice
    public List<Edge> adj(int v) {
        return adj.get(v);
    }

    public List<Edge> edges() {
        List<Edge> allEdges = new ArrayList<>();
        for (List<Edge> edges : adj) {
            allEdges.addAll(edges);
        }
        return allEdges;
    }

    public int getV() {
        return V;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int v = 0; v < V; v++) {
            sb.append(v).append(": ");
            for (Edge edge : adj.get(v)) {
                sb.append(edge).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}