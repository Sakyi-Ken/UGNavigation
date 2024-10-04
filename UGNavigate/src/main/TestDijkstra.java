package main;

import routeFinder.*;

import java.util.Map;

public class TestDijkstra {
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

    // Run Dijkstra's algorithm
    DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();
    Map<Node, Integer> distances = dijkstra.findShortestPath(graph, balmeLibrary); // Get distances

    // Output the shortest paths from Balme Library
    System.out.println("Shortest paths from Balme Library:");
    for (Map.Entry<Node, Integer> entry : distances.entrySet()) {
      System.out.println(" - To " + entry.getKey().getName() + ": " + entry.getValue() + " units");
    }

    // Get and print the path to Commonwealth Hall
    Node targetNode = commonwealthHall;
    System.out.println("\nPath to " + targetNode.getName() + ":");
    for (Node node : dijkstra.getShortestPathTo(targetNode)) {
      System.out.print(node.getName() + " -> ");
    }
    System.out.println("End");
  }
}
