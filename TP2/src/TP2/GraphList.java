package TP2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import TP2.VertexAttributes.Color;

public class GraphList implements Graph{
    static final Integer INF = 1000000000;
    static final Integer NIL = -1;
    private final int V; //n de vértices
    private int E;       //n de arestas
    private Vector<Vector<Edge>> adj; //listas de adjacências
    private Vector<VertexAttributes> atribs;
    private List<Edge> edges; //lista de arestas


    //grafo vazio com V vértices
    public GraphList(int V) {
        this.V = V;
        this.E = 0;
        edges = new ArrayList<>();
        adj = new Vector<>(V);

        for (int v = 0; v < V; v++)
            adj.add(new Vector<>());
        atribs = new Vector<>(V);
        for (int v = 0; v < V; v++)
            atribs.add(new VertexAttributes());
    }

    class In {
        private Scanner in;

        public In(File file) {
            try {
                this.in = new Scanner(file);
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + file.getName());
            }
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    //adiciona uma aresta ponderada entre os vértices v e w
    public void addEdge(int v, int w, double weight) {
        Edge edge = new Edge(v, w, weight);
        edges.add(edge);

        adj.get(v).add(edge);//diciona na lista de v
        adj.get(w).add(new Edge(w, v, weight));//adiciona na lista de w (para grafos não direcionados)
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj.get(v);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(V + " vertices, " + E + " edges\n");
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for (Edge e : adj.get(v))
                s.append(e).append(" ");
            s.append("\n");
        }
        return s.toString();
    }

    public String StatusAtribs() {
        StringBuilder s = new StringBuilder("Vertex Attributes Status (color, pred, d, f)\n");
        int i = 0;
        for (VertexAttributes a : atribs) {
            s.append(i).append(": ")
            .append( a.getColor() ).append(", ")
            .append( a.getPred() ).append(", ")
            .append( a.getD() ).append(", ")
            .append( a.getF() ).append("\n");
            i++;
        }
        return s.toString();
    }

    public List<Edge> edges() {
        return edges;
    }


    //BFS (Busca em Largura)
    public void BFS(int s) {
        Queue<Integer> Q = new LinkedList<>();
        
        for (VertexAttributes a : atribs) {
             a.setColor(Color.WHITE);
             a.setD(INF);
             a.setPred(NIL);
        }

        atribs.get(s).setColor(Color.GRAY);
        atribs.get(s).setD(0);
        atribs.get(s).setPred(NIL);
        Q.add(s);
        
        while (!Q.isEmpty()) {
            int u = Q.poll();
            for (Edge e : adj.get(u)) {
                int v = e.w; //destino da aresta
                if (atribs.get(v).getColor() == Color.WHITE) {
                    atribs.get(v).setColor(Color.GRAY);
                    atribs.get(v).setD( atribs.get(u).getD() + 1 );
                    atribs.get(v).setPred(u);
                    Q.add(v);
                }
            }
            atribs.get(u).setColor(Color.BLACK);
        }
    }

    
    //DFS (Busca em Profundidade)
    public void DFS() {
        int tempo = 0;

        for (VertexAttributes a : atribs) {
             a.setColor(Color.WHITE);
             a.setPred(NIL);
             a.setD(0);
             a.setF(0);
        }

        for (int u = 0; u < V; u++) {
            if (atribs.get(u).getColor() == Color.WHITE) {
                tempo = DFS_VISIT(u, tempo);
            }
        }
    }

    private int DFS_VISIT(int u, int tempo) {
        tempo += 1;
        VertexAttributes uAtrib = atribs.get(u);
        uAtrib.setD(tempo);
        uAtrib.setColor(Color.GRAY);

        for (Edge e : adj.get(u)) {
            int v = e.w; //destino da aresta
            VertexAttributes vAtrib = atribs.get(v);
            if (vAtrib.getColor() == Color.WHITE) {
                vAtrib.setPred(u);
                tempo = DFS_VISIT(v, tempo);
            }
        }

        uAtrib.setColor(Color.BLACK);
        tempo += 1;
        uAtrib.setF(tempo);
        return tempo;
    }

}