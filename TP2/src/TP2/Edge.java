package TP2;

public class Edge {
    int v, w;     // Vértices conectados pela aresta
    double weight; // Peso da aresta

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("(%d - %d, weight: %.2f)", v, w, weight);
    }
}