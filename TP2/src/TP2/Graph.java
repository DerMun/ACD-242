package TP2;

import java.util.List;

public interface Graph {

    // Retorna o número de vértices no grafo
    int V();

    // Retorna o número de arestas no grafo
    int E();

    // Adiciona uma aresta ponderada entre os vértices v e w
    void addEdge(int v, int w, double weight);

    List<Edge> edges();

    Iterable<Edge> adj(int v);

    void BFS(int s);

    void DFS();

    // Retorna o status dos atributos dos vértices (cor, predecessor, d, f)
    String StatusAtribs();

    String toString();
}

