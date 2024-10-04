package main;

import java.util.List;

import routeFinder.*;

public class UGNavigate {
  public static Graph createGraph() {
    Graph graph = new Graph();

    // Create nodes
    Node balmeLibrary = new Node("Balme Library", 0, 0);
    Node centralCafeteria = new Node("Central Cafeteria", 2, 3);
    Node voltaHall = new Node("Volta Hall", 5, 1);
    Node commonwealthHall = new Node("Commonwealth Hall", 8, 7);
    Node akuafoHall = new Node("Akuafo Hall", 10, 2);
    Node jqb = new Node("JQB", 6, 8);
    Node nBlock = new Node("N-Block", 3, 12);
    Node sportsCenter = new Node("Sports Center", 15, 5);
    Node greatHall = new Node("Great Hall", 12, 10);
    Node mainGate = new Node("Main Gate", 20, 5);
    Node ictDepartment = new Node("ICT Department", 18, 2);

    // Add nodes to the graph
    graph.addNode(balmeLibrary);
    graph.addNode(centralCafeteria);
    graph.addNode(voltaHall);
    graph.addNode(commonwealthHall);
    graph.addNode(akuafoHall);
    graph.addNode(jqb);
    graph.addNode(nBlock);
    graph.addNode(sportsCenter);
    graph.addNode(greatHall);
    graph.addNode(mainGate);
    graph.addNode(ictDepartment);

    // Define neighbors and distances
    balmeLibrary.addNeighbor(centralCafeteria, 5);
    balmeLibrary.addNeighbor(voltaHall, 10);
    balmeLibrary.addNeighbor(ictDepartment, 15);
    centralCafeteria.addNeighbor(commonwealthHall, 7);
    voltaHall.addNeighbor(akuafoHall, 3);
    commonwealthHall.addNeighbor(nBlock, 8);
    akuafoHall.addNeighbor(jqb, 6);
    jqb.addNeighbor(ictDepartment, 4);
    sportsCenter.addNeighbor(nBlock, 9);
    greatHall.addNeighbor(mainGate, 10);
    sportsCenter.addNeighbor(greatHall, 7);

    return graph;
  }

  public static void visualizePath(List<Node> path) {
    System.out.println("\nMap Visualization:");
    System.out.println("--------------------");
    for (Node node : path) {
      System.out.print(node.getName() + " -> ");
    }
    System.out.println("End");
    System.out.println("--------------------");
  }

  public static void main(String[] args) {
    Graph graph = createGraph();

    // Run A* algorithm from Balme Library to ICT Department
    AStarAlgorithm aStar = new AStarAlgorithm();
    Node start = graph.getNodes().get(0); // Balme Library
    Node goal = graph.getNodes().get(10); // ICT Department
    List<Node> path = aStar.findPath(graph, start, goal);

    // Visualize the path
    System.out.println("Path from " + start.getName() + " to " + goal.getName() + ":");
    visualizePath(path);
  }
}
