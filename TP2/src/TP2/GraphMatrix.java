package TP2;

import java.util.*;

public class GraphMatrix implements Graph{
    private static final int INF = Integer.MAX_VALUE;
    private final int V; //n de vértices
    private int E;       //n de arestas
    private double[][] adjMatrix; //matriz de adjacência
    private List<VertexAttributes> atribs;
    private List<Edge> edges; //lista de arestas do grafo


    public GraphMatrix(int V) {
        this.V = V;
        this.E = 0;
        adjMatrix = new double[V][V];
        atribs = new ArrayList<>();
        edges = new ArrayList<>();

        //inicializa a matriz de adjacência com valores de infinito (sem conexão)
        for (int i = 0; i < V; i++) {
            Arrays.fill(adjMatrix[i], INF);
            adjMatrix[i][i] = 0; //distância para si mesmo é 0
            atribs.add(new VertexAttributes());
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public List<Edge> edges() {
        return edges;
    }

    public void addEdge(int v, int w, double weight) {
        if (v >= 0 && v < V && w >= 0 && w < V) {
            if (adjMatrix[v][w] == INF) { //se não havia conexão, incrementa o número de arestas
                E++;
            }
            adjMatrix[v][w] = weight;
            adjMatrix[w][v] = weight;
            edges.add(new Edge(v, w, weight));
        } else {
            throw new IllegalArgumentException("Vértices fora dos limites do grafo");
        }
    }

    //peso da aresta entre dois vértices
    public double getEdgeWeight(int v, int w) {
        return adjMatrix[v][w];
    }

    //vizinhos de um vértice
    public List<Integer> neighbors(int v) {
        List<Integer> neighbors = new ArrayList<>();
        for (int w = 0; w < V; w++) {
            if (adjMatrix[v][w] != INF && v != w) {
                neighbors.add(w);
            }
        }
        return neighbors;
    }

    public Iterable<Edge> adj(int v) {
        List<Edge> incidentEdges = new ArrayList<>();
        for (int w = 0; w < V; w++) {
            if (adjMatrix[v][w] != INF && v != w) {
                incidentEdges.add(new Edge(v, w, adjMatrix[v][w]));
            }
        }
        return incidentEdges;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(V + " vertices, " + E + " edges\n");
        for (int i = 0; i < V; i++) {
            s.append(i).append(": ");
            for (int j = 0; j < V; j++) {
                if (adjMatrix[i][j] != INF) {
                    s.append(j).append("(").append(adjMatrix[i][j]).append(") ");
                }
            }
            s.append("\n");
        }
        return s.toString();
    }

    public String StatusAtribs() {
        StringBuilder s = new StringBuilder("Vertex Attributes Status (color, pred, d, f)\n");
        int i = 0;
        for (VertexAttributes a : atribs) {
            s.append(i).append(": ")
                    .append(a.getColor()).append(", ")
                    .append(a.getPred()).append(", ")
                    .append(a.getD()).append(", ")
                    .append(a.getF()).append("\n");
            i++;
        }
        return s.toString();
    }

    //BFS (Busca em Largura)
    public void BFS(int s) {
        Queue<Integer> Q = new LinkedList<>();

        for (VertexAttributes a : atribs) {
            a.setColor(VertexAttributes.Color.WHITE);
            a.setD(INF);
            a.setPred(-1);
        }

        atribs.get(s).setColor(VertexAttributes.Color.GRAY);
        atribs.get(s).setD(0);
        atribs.get(s).setPred(-1);
        Q.add(s);

        while (!Q.isEmpty()) {
            int u = Q.poll();
            for (int v : neighbors(u)) {
                if (atribs.get(v).getColor() == VertexAttributes.Color.WHITE) {
                    atribs.get(v).setColor(VertexAttributes.Color.GRAY);
                    atribs.get(v).setD(atribs.get(u).getD() + 1);
                    atribs.get(v).setPred(u);
                    Q.add(v);
                }
            }
            atribs.get(u).setColor(VertexAttributes.Color.BLACK);
        }
    }

    //DFS (Busca em Profundidade)
    public void DFS() {
        int tempo = 0;

        for (VertexAttributes a : atribs) {
            a.setColor(VertexAttributes.Color.WHITE);
            a.setPred(-1);
            a.setD(0);
            a.setF(0);
        }

        for (int u = 0; u < V; u++) {
            if (atribs.get(u).getColor() == VertexAttributes.Color.WHITE) {
                tempo = DFS_VISIT(u, tempo);
            }
        }
    }

    private int DFS_VISIT(int u, int tempo) {
        tempo += 1;
        VertexAttributes uAtrib = atribs.get(u);
        uAtrib.setD(tempo);
        uAtrib.setColor(VertexAttributes.Color.GRAY);

        for (int v : neighbors(u)) {
            VertexAttributes vAtrib = atribs.get(v);
            if (vAtrib.getColor() == VertexAttributes.Color.WHITE) {
                vAtrib.setPred(u);
                tempo = DFS_VISIT(v, tempo);
            }
        }

        uAtrib.setColor(VertexAttributes.Color.BLACK);
        tempo += 1;
        uAtrib.setF(tempo);
        return tempo;
    }
}