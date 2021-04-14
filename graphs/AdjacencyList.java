package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class AdjacencyList {

    int edges;
    int vertices;

    ArrayList<ArrayList<Integer>> adjList;

    public AdjacencyList(int v) {

        vertices = v;
        adjList = new ArrayList<>(v);
        createAdjacencyList(v);
    }

    public AdjacencyList() {
    }

    public void addEdge(int u, int v) {

        // for undirected graph
        adjList.get(u).add(v);
        adjList.get(v).add(u);

    }

    public void showAdjacencyList() {
        System.out.println(adjList);
    }

    private void createAdjacencyList(int v) {

        for (int i = 0; i < v; i++) {
            ArrayList<Integer> l = new ArrayList<>();
            adjList.add(l);
        }
    }

    /**
     * BFS algorithm for graphs:
     */

    public void bfs(int sourceVertex) {

        Queue<Integer> q = new LinkedList<>();
        boolean visited[] = new boolean[vertices];

        q.add(sourceVertex);
        visited[sourceVertex] = true;

        while (!q.isEmpty()) {

            int k = q.poll();
            System.out.print(k + " ");

            for (int x : adjList.get(k)) {

                if (visited[x] == false) {

                    q.add(x);
                    visited[x] = true;
                }
            }

        }
        System.out.println();
    }

    /**
     * DFS algorithm for graphs:
     */

    public void dfs(int sourceVertex) {

        boolean visited[] = new boolean[vertices];
        dfsRec(sourceVertex, visited);
        System.out.println();
    }

    private void dfsRec(int s, boolean[] visited) {

        visited[s] = true;
        System.out.print(s + " ");

        for (int u : adjList.get(s)) {

            if (visited[u] == false) {
                dfsRec(u, visited);
            }
        }

    }

    /**
     * Shortest Path
     */

    public Map<Integer, Integer> shortestPath(int source) {

        Queue<Integer> q = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < vertices; i++) {

            if (i == source) {
                map.put(source, 0);
                continue;
            }

            map.put(i, Integer.MAX_VALUE);
        }

        boolean visited[] = new boolean[vertices];
        q.add(source);
        visited[source] = true;

        while (!q.isEmpty()) {

            int k = q.poll();

            for (int u : adjList.get(k)) {

                if (!visited[u]) {

                    q.add(u);
                    int cnt = 1 + map.get(k);
                    map.put(u, cnt);
                    visited[u] = true;

                }
            }

        }

        return map;
    }

    /**
     * Detect cycle in undirected graph: Check if there is a adjacent to a node
     * which is not it's parent currently and is still visited then there is cycle.
     */

    public boolean containsCycle() {

        boolean visited[] = new boolean[vertices];

        // handling for disconnected graphs
        for (int i = 0; i < vertices; i++) {

            if (!visited[i]) {

                if (cycleDfs(visited, i, -1))
                    return true;
            }
        }

        return false;
    }

    /**
     * Algo:
     * If you have reached to a child using a source 
     * which is already visited and it is currently not the parent then cycle exists.
     */
    private boolean cycleDfs(boolean visited[], int source, int parent) {

        visited[source] = true;

        for (int u : adjList.get(source)) {

            if (!visited[u]) {

                if (cycleDfs(visited, u, source))
                    return true;
                 }

            else if (u != parent)
                    return true;
        }

        return false;
    }

}