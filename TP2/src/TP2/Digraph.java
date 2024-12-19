package TP2;

import java.util.List;

public interface Digraph {
    // adicionar uma aresta ao digrafo
    void addEdge(int v, int w, double weight);

    // obter todas as arestas do digrafo
    List<Edge> edges();

    List<Edge> adj(int v);

    int getV();
}
