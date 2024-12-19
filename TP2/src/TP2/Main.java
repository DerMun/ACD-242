package TP2;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int numVertices = 5;

        Graph graphList = new GraphList(numVertices);
        Graph graphMatrix = new GraphMatrix(numVertices);
        addEdgesLG(graphList);
        addEdgesMG(graphMatrix);

        Digraph digraphList = new DigraphList(numVertices);
        Digraph digraphMatrix = new DigraphMatrix(numVertices);
        addEdgesLD(digraphList);
        addEdgesMD(digraphMatrix);


        /*Digraph digraphList = null;
        Digraph digraphMatrix = null;
        try {
            File file = new File("src/RandomGraphSamples/sample500-249500.gr");

            digraphList = GraphReader.readAsList(file);
            System.out.println(digraphList);

            digraphMatrix = GraphReader.readAsMatrix(file);
            System.out.println(digraphMatrix);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }*/


        // Menu de opções
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMENU");
            System.out.println("1. Busca em Largura (BFS)");
            System.out.println("2. Busca em Profundidade (DFS)");
            System.out.println("3. Ordenação Topológica");
            System.out.println("4. Árvore Geradora Mínima (Kruskal)");
            System.out.println("5. Árvore Geradora Mínima (Prim)");
            System.out.println("6. Caminho Mínimo de Origem Única (Dijkstra)");
            System.out.println("7. Caminho Mínimo de Origem Única (Bellman-Ford)");
            System.out.println("8. Caminho Mínimo entre Todos os Pares (Floyd-Warshall)");
            System.out.println("9. Fluxo Máximo (Ford-Fulkerson)");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int option = scanner.nextInt();
            if (option == 0) break;

            long startTime, endTime;
            int exe = 1;
            switch (option) {

                case 1: // Busca em Largura (BFS)
                System.out.println("\nGrafo com Lista de Adjacências:");
                System.out.println(graphList);//toString
                System.out.println("Grafo com Matriz de Adjacências:");
                System.out.println(graphMatrix);//toString

                System.out.println("Algoritmo: Busca em Largura (BFS)");
                int source = 0;

                System.out.println("\nGrafo com Lista de Adjacências exe");
                for(int i = 0; i < exe; i++){
                    startTime = System.nanoTime();
                    graphList.BFS(source);
                    endTime = System.nanoTime();
                    System.out.println("Tempo de execução: " + (endTime - startTime) + " ns");
                }
                System.out.println(graphList.StatusAtribs());

                System.out.println("\nGrafo com Matriz de Adjacências exe");
                for(int i = 0; i < exe; i++) {
                    startTime = System.nanoTime();
                    graphMatrix.BFS(source);
                    endTime = System.nanoTime();
                    System.out.println("Tempo de execução: " + (endTime - startTime) + " ns");
                }
                System.out.println(graphMatrix.StatusAtribs());
                break;


                case 2: // Busca em Profundidade (DFS)
                System.out.println("\nGrafo com Lista de Adjacências:");
                System.out.println(graphList);//toString
                System.out.println("Grafo com Matriz de Adjacências:");
                System.out.println(graphMatrix);//toString

                System.out.println("Algoritmo: Busca em Profundidade (DFS)");

                System.out.println("\nGrafo com Lista de Adjacências exe");
                for(int i = 0; i < exe; i++) {
                    startTime = System.nanoTime();
                    graphList.DFS();
                    endTime = System.nanoTime();
                    System.out.println("Tempo de execução: " + (endTime - startTime) + " ns");
                }
                System.out.println(graphList.StatusAtribs());

                System.out.println("\nGrafo com Matriz de Adjacências exe");
                for(int i = 0; i < exe; i++) {
                    startTime = System.nanoTime();
                    graphMatrix.DFS();
                    endTime = System.nanoTime();
                    System.out.println("Tempo de execução: " + (endTime - startTime) + " ns");
                }
                System.out.println(graphMatrix.StatusAtribs());
                break;


                case 3: // Ordenação Topológica
                System.out.println("\nDigrafo com Lista de Adjacências:");
                System.out.println(digraphList);//toString
                System.out.println("Digrafo com Matriz de Adjacências:");
                System.out.println(digraphMatrix);//toString

                System.out.println("Algoritmo: Ordenação Topológica");

                System.out.println("\nDigrafo com Graph Lista de Adjacências exe");
                TopologicalSort topoSortList = new TopologicalSort(digraphList);
                for(int i = 0; i < exe; i++) {
                    startTime = System.nanoTime();
                    System.out.println(topoSortList.sort());
                    endTime = System.nanoTime();
                    System.out.println("Tempo de execução: " + (endTime - startTime) + " ns");
                }

                System.out.println("\nDigrafo com Graph Matriz de Adjacências exe");
                TopologicalSort topoSortMatrix = new TopologicalSort(digraphMatrix);
                for(int i = 0; i < exe; i++) {
                    startTime = System.nanoTime();
                    System.out.println(topoSortMatrix.sort());
                    endTime = System.nanoTime();
                    System.out.println("Tempo de execução: " + (endTime - startTime) + " ns");
                }
                break;


                case 4: // Árvore Geradora Mínima (Kruskal)
                System.out.println("\nGrafo com Lista de Adjacências:");
                System.out.println(graphList);//toString
                System.out.println("Grafo com Matriz de Adjacências:");
                System.out.println(graphMatrix);//toString

                System.out.println("Algoritmo: Árvore Geradora Mínima (Kruskal)");

                System.out.println("\nGrafo com Lista de Adjacências exe");
                for(int i = 0; i < exe; i++) {
                    startTime = System.nanoTime();
                    System.out.println("Peso da MST: " + Kruskal.findMST(graphList));
                    endTime = System.nanoTime();
                    System.out.println("Tempo de execução: " + (endTime - startTime) + " ns");
                }

                System.out.println("\nGrafo com Matriz de Adjacências exe");
                for(int i = 0; i < exe; i++) {
                    startTime = System.nanoTime();
                    System.out.println("Peso da MST: " + Kruskal.findMST(graphMatrix));
                    endTime = System.nanoTime();
                    System.out.println("Tempo de execução: " + (endTime - startTime) + " ns");
                }
                break;


                case 5: // Árvore Geradora Mínima (Prim)
                    System.out.println("\nGrafo com Lista de Adjacências:");
                    System.out.println(graphList);//toString
                    System.out.println("Grafo com Matriz de Adjacências:");
                    System.out.println(graphMatrix);//toString

                    System.out.println("Algoritmo: Árvore Geradora Mínima (Prim)");

                    System.out.println("\nGrafo com Lista de Adjacências exe");
                    for(int i = 0; i < exe; i++) {
                        startTime = System.nanoTime();
                        System.out.println("Peso da MST: " + Prim.findMST(graphList));
                        endTime = System.nanoTime();
                        System.out.println("Tempo de execução: " + (endTime - startTime) + " ns");
                    }

                    System.out.println("\nGrafo com Matriz de Adjacências exe");
                    for(int i = 0; i < exe; i++) {
                        startTime = System.nanoTime();
                        System.out.println("Peso da MST: " + Prim.findMST(graphMatrix));
                        endTime = System.nanoTime();
                        System.out.println("Tempo de execução: " + (endTime - startTime) + " ns");
                    }
                    break;

                case 6: // Caminho Mínimo de Origem Única (Dijkstra)
                    System.out.println("\nDigrafo com Lista de Adjacências:");
                    System.out.println(digraphList);//toString
                    System.out.println("Digrafo com Matriz de Adjacências:");
                    System.out.println(digraphMatrix);//toString

                    System.out.println("Algoritmo: Caminho Mínimo (Dijkstra)");

                    System.out.println("\nDigrafo Lista de Adjacências exe");
                    Dijkstra dijkstraList = new Dijkstra(digraphList);
                    for(int i = 0; i < exe; i++) {
                        startTime = System.nanoTime();
                        dijkstraList.findShortestPaths(0);
                        endTime = System.nanoTime();
                        System.out.println("Tempo de execução: " + (endTime - startTime) + " ns");
                    }

                    for (int v = 0; v < digraphList.getV(); v++) {
                        System.out.printf("Distância até %d: %.2f\n", v, dijkstraList.getDistance(v));
                        System.out.println("Caminho: " + dijkstraList.getPath(v));
                    }

                    System.out.println("\nDigrafo Matriz de Adjacências exe");
                    Dijkstra dijkstraMatrix = new Dijkstra(digraphMatrix);
                    for(int i = 0; i < exe; i++) {
                        startTime = System.nanoTime();
                        dijkstraMatrix.findShortestPaths(0);
                        endTime = System.nanoTime();
                        System.out.println("Tempo de execução: " + (endTime - startTime) + " ns");
                    }

                    for (int v = 0; v < digraphMatrix.getV(); v++) {
                        System.out.printf("Distância até %d: %.2f\n", v, dijkstraMatrix.getDistance(v));
                        System.out.println("Caminho: " + dijkstraMatrix.getPath(v));
                    }
                    break;

                case 7: // Caminho Mínimo de Origem Única (Bellman-Ford)
                    System.out.println("\nDigrafo com Lista de Adjacências:");
                    System.out.println(digraphList);//toString
                    System.out.println("Digrafo com Matriz de Adjacências:");
                    System.out.println(digraphMatrix);//toString

                    System.out.println("Algoritmo: Caminho Mínimo (Bellman-Ford)");

                    System.out.println("\nDigrafo Lista de Adjacências exe");
                    BellmanFord bellmanFordList = new BellmanFord(digraphList);
                    boolean hasSolutionList = false;
                    for(int i = 0; i < exe; i++) {
                        startTime = System.nanoTime();
                        hasSolutionList = bellmanFordList.findShortestPaths(0);
                        endTime = System.nanoTime();
                        System.out.println("Tempo de execução: " + (endTime - startTime) + " ns");
                    }

                    if (hasSolutionList) {
                        System.out.println("Sem ciclos negativos detectados para Lista de Adjacências.");
                        for (int v = 0; v < digraphList.getV(); v++) {
                            System.out.printf("Distância até %d: %.2f\n", v, bellmanFordList.getDistance(v));
                            System.out.println("Caminho: " + bellmanFordList.getPath(v));
                        }
                    } else {
                        System.out.println("Ciclo negativo detectado na Lista de Adjacências!");
                    }

                    System.out.println("\nDigrafo Matriz de Adjacências exe");
                    BellmanFord bellmanFordMatrix = new BellmanFord(digraphMatrix);
                    boolean hasSolutionMatrix = false;
                    for(int i = 0; i < exe; i++) {
                        startTime = System.nanoTime();
                        hasSolutionMatrix = bellmanFordMatrix.findShortestPaths(0);
                        endTime = System.nanoTime();
                        System.out.println("Tempo de execução: " + (endTime - startTime) + " ns");
                    }

                    if (hasSolutionMatrix) {
                        System.out.println("Sem ciclos negativos detectados para Matriz de Adjacências.");
                        for (int v = 0; v < digraphMatrix.getV(); v++) {
                            System.out.printf("Distância até %d: %.2f\n", v, bellmanFordMatrix.getDistance(v));
                            System.out.println("Caminho: " + bellmanFordMatrix.getPath(v));
                        }
                    } else {
                        System.out.println("Ciclo negativo detectado na Matriz de Adjacências!");
                    }
                    break;

                case 8: // Caminho Mínimo entre Todos os Pares (Floyd-Warshall)
                    System.out.println("\nDigrafo com Lista de Adjacências:");
                    System.out.println(digraphList);//toString
                    System.out.println("Digrafo com Matriz de Adjacências:");
                    System.out.println(digraphMatrix);//toString

                    System.out.println("Algoritmo: Caminho Mínimo (Floyd-Warshall)");

                    System.out.println("\nDigrafo com Lista de Adjacências exe");
                    FloydWarshall fwList = new FloydWarshall(digraphList);
                    for(int i = 0; i < exe; i++) {
                        startTime = System.nanoTime();
                        fwList.computeShortestPaths();
                        endTime = System.nanoTime();
                        System.out.println("Tempo de execução: " + (endTime - startTime) + " ns");
                    }

                    System.out.println("Distâncias mínimas:");
                    fwList.printDistances();

                    System.out.println("\nDigrafo com Matriz de Adjacências exe");
                    FloydWarshall fwMatrix = new FloydWarshall(digraphMatrix);
                    for(int i = 0; i < exe; i++) {
                        startTime = System.nanoTime();
                        fwMatrix.computeShortestPaths();
                        endTime = System.nanoTime();
                        System.out.println("Tempo de execução: " + (endTime - startTime) + " ns");
                    }

                    System.out.println("Distâncias mínimas:");
                    fwMatrix.printDistances();
                    break;

                case 9: // Fluxo Máximo (Ford-Fulkerson)
                    System.out.println("\nDigrafo com Lista de Adjacências:");
                    System.out.println(digraphList);//toString
                    System.out.println("Digrafo com Matriz de Adjacências:");
                    System.out.println(digraphMatrix);//toString

                    System.out.println("Algoritmo: Fluxo Máximo (Ford-Fulkerson)");

                    System.out.println("\nDigrafo com Lista de Adjacências exe");
                    MaxFlow maxFlowList = new MaxFlow(digraphList);
                    for(int i = 0; i < exe; i++) {
                        startTime = System.nanoTime();
                        System.out.println("Fluxo máximo: " + maxFlowList.fordFulkerson(0, 4));
                        endTime = System.nanoTime();
                        System.out.println("Tempo de execução: " + (endTime - startTime) + " ns");
                    }

                    System.out.println("\nDigrafo com Matriz de Adjacências exe");
                    MaxFlow maxFlowMatrix = new MaxFlow(digraphMatrix);
                    for(int i = 0; i < exe; i++) {
                        startTime = System.nanoTime();
                        System.out.println("Fluxo máximo: " + maxFlowMatrix.fordFulkerson(0, 4));
                        endTime = System.nanoTime();
                        System.out.println("Tempo de execução: " + (endTime - startTime) + " ns");
                    }
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }

    private static void addEdgesLG(Graph graph) {
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 5);
        graph.addEdge(3, 4, 3);
    }

    private static void addEdgesMG(Graph graph) {
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 5);
        graph.addEdge(3, 4, 3);
    }

    private static void addEdgesLD(Digraph graph) {
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 5);
        graph.addEdge(3, 4, 3);
    }

    private static void addEdgesMD(Digraph graph) {
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 5);
        graph.addEdge(3, 4, 3);
    }



}