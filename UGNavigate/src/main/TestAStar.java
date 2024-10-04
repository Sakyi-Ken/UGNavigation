package main;

import routeFinder.*;

import java.util.List;

public class TestAStar {
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

    // Run A* algorithm
    AStarAlgorithm aStar = new AStarAlgorithm();
    List<Node> path = aStar.findPath(graph, balmeLibrary, commonwealthHall);

    // Output the path from Balme Library to Commonwealth Hall
    System.out.println("Path from Balme Library to Commonwealth Hall:");
    for (Node node : path) {
      System.out.println(" - " + node.getName());
    }
  }
}
