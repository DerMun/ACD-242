package TP2;

import java.util.*;

public class TopologicalSort {
    private final Digraph graph;
    private final boolean[] visited;
    private final Stack<Integer> stack;

    public TopologicalSort(Digraph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.getV()];
        this.stack = new Stack<>();
    }

    public List<Integer> sort() {
        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) {
                dfs(v);
            }
        }

        //converte a pilha para uma lista na ordem correta
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    private void dfs(int v) {
        visited[v] = true;

        for (Edge edge : graph.adj(v)) {
            if (!visited[edge.w]) {
                dfs(edge.w);
            }
        }

        stack.push(v);
    }
}