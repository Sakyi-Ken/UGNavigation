package main;

import routeFinder.*;

public class TestFloydWarshall {
  public static void main(String[] args) {
    // Create a new graph
    Graph graph = new Graph();

    // Create nodes
    Node balmeLibrary = new Node("Balme Library", 0, 0);
    Node centralCafeteria = new Node("Central Cafeteria", 2, 3);
    Node voltaHall = new Node("Volta Hall", 5, 1);
    Node commonwealthHall = new Node("Commonwealth Hall", 8, 7);

    // Add nodes to the graph
    graph.addNode(balmeLibrary);
    graph.addNode(centralCafeteria);
    graph.addNode(voltaHall);
    graph.addNode(commonwealthHall);

    // Define neighbors and distances
    balmeLibrary.addNeighbor(centralCafeteria, 5);
    balmeLibrary.addNeighbor(voltaHall, 10);
    centralCafeteria.addNeighbor(commonwealthHall, 7);
    voltaHall.addNeighbor(commonwealthHall, 3);

    // Run Floyd-Warshall algorithm
    FloydWarshallAlgorithm floydWarshall = new FloydWarshallAlgorithm();
    int[][] shortestPaths = floydWarshall.findShortestPaths(graph);

    // Output the shortest paths between all pairs of nodes
    System.out.println("Shortest paths between all pairs of nodes:");
    Node[] nodes = graph.getNodes().toArray(new Node[0]);
    for (int i = 0; i < nodes.length; i++) {
      for (int j = 0; j < nodes.length; j++) {
        System.out.println(
            " - From " + nodes[i].getName() + " to " + nodes[j].getName() + ": " + shortestPaths[i][j] + " units");
      }
    }
  }
}
