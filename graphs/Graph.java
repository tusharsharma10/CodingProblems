package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Graph {

    /**
     * Takes matrix of size v*2 and creates the graph for it. For undirected graph.
     */
    
    public AdjacencyList createGraph(int mat[][], int vertices) {

        AdjacencyList list = new AdjacencyList(vertices);
        int edges = mat.length;
        for (int i = 0; i < edges; i++) {

            list.addEdge(mat[i][0], mat[i][1]);

        }

        return list;
    }

    

    

    

    public static void main(String[] args) {

        // Graph g = new Graph();

        // int mat[][] = { { 0, 2 }, { 2, 4 }, { 2, 3 }, { 0, 1 }, { 1, 3 }, { 3, 5 }, { 4, 5 }, { 0, 4 } };

        // AdjacencyList list = g.createGraph(mat, 7);

        // list.showAdjacencyList();

        // System.out.println(list.shortestPath(0));


        Graph g2 = new Graph();
        
        int mat2[][] = { { 0, 1 }, { 1,2 }, { 2, 3 }, {1,3},{ 2,4 }, { 4, 5 }};

        AdjacencyList list = g2.createGraph(mat2, 6);

        list.showAdjacencyList();

        System.out.println(list.containsCycle());
    }
}
