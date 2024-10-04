package main;

import routeFinder.Graph;
import routeFinder.Node;

public class TestGraph {
  public static void main(String[] args) {
    // Create a new graph
    Graph graph = new Graph();

    // Create nodes
    Node balmeLibrary = new Node("Balme Library", 0, 0);
    Node centralCafeteria = new Node("Central Cafeteria", 2, 3);
    Node voltaHall = new Node("Volta Hall", 5, 1);

    // Add nodes to the graph
    graph.addNode(balmeLibrary);
    graph.addNode(centralCafeteria);
    graph.addNode(voltaHall);

    // Add neighbors
    balmeLibrary.addNeighbor(centralCafeteria, 5);
    balmeLibrary.addNeighbor(voltaHall, 10);
    centralCafeteria.addNeighbor(voltaHall, 3);

    // Output all nodes in the graph
    System.out.println("Graph nodes:");
    for (Node node : graph.getNodes()) {
      System.out.println(" - " + node.getName());
    }

    // Output the distance between nodes
    int distance = graph.getDistance(0, 2); // Distance from Balme Library to Volta Hall
    System.out.println("Distance from Balme Library to Volta Hall: " + distance);
  }
}
