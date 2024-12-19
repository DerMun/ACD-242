package TP2;

import java.util.ArrayList;
import java.util.List;

// Representa um digrafo ponderado usando matriz de adjacência
public class DigraphMatrix implements Digraph{
    private final int V;//n de vértices
    private final double[][] adjMatrix;//matriz de adjacência

    public DigraphMatrix(int V){
        this.V = V;
        adjMatrix = new double[V][V];

        //inicializa a matriz com infinito (ausência de arestas)
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                adjMatrix[i][j] = Double.POSITIVE_INFINITY;
            }
        }
    }

    public void addEdge(int v, int w, double weight) {
        adjMatrix[v][w] = weight;
    }

    //peso da aresta de v para w (ou infinito se não existir)
    public double getEdgeWeight(int v, int w) {
        return adjMatrix[v][w];
    }

    //todas as arestas do grafo como uma lista
    public List<Edge> edges() {
        List<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (adjMatrix[i][j] != Double.POSITIVE_INFINITY) {
                    edgeList.add(new Edge(i, j, adjMatrix[i][j])); // Cria e adiciona a aresta
                }
            }
        }
        return edgeList;
    }


    public List<Edge> adj(int v) {
        List<Edge> edges = new ArrayList<>();
        for (int w = 0; w < V; w++) {
            if (adjMatrix[v][w] != Double.POSITIVE_INFINITY) {
                edges.add(new Edge(v, w, adjMatrix[v][w]));
            }
        }
        return edges;
    }



    public int getV() {
        return V;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < V; i++) {
            sb.append(i).append(": ");
            for (int j = 0; j < V; j++) {
                if (adjMatrix[i][j] != Double.POSITIVE_INFINITY) {
                    sb.append(String.format("(%d, weight: %.2f) ", j, adjMatrix[i][j]));
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}