package TP2;

public class FloydWarshall {
    private static final double INF = Double.POSITIVE_INFINITY;
    private final int V;
    private final double[][] dist;

    public FloydWarshall(Digraph graph) {
        this.V = graph.getV();
        this.dist = new double[V][V];

        //inicializa a matriz de distâncias com base no grafo
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = INF;
                }
            }
        }

        for (int u = 0; u < V; u++) {
            for (Edge edge : graph.adj(u)) {
                dist[edge.v][edge.w] = edge.weight;
            }
        }
    }

    //método para calcular o caminho mínimo entre todos os pares de vértices
    public void computeShortestPaths() {
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
    }

    public double[][] getDistances() {
        return dist;
    }

    public void printDistances() {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.printf("%.2f ", dist[i][j]);
                }
            }
            System.out.println();
        }
    }
}

