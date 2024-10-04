package main;

import UI.MapVisualizer;
import routeFinder.*;

import java.util.List;

public class TestVisualization {

  public static void main(String[] args) {
    // Create a new graph
    Graph graph = new Graph();

    // Create nodes
    Node balmeLibrary = new Node("Balme Library", 50, 50);
    Node centralCafeteria = new Node("Central Cafeteria", 200, 80);
    Node voltaHall = new Node("Volta Hall", 350, 50);
    Node commonwealthHall = new Node("Commonwealth Hall", 500, 150);
    Node akuaffoHall = new Node("Akuafo Hall", 100, 250);
    Node nBlock = new Node("N-Block", 250, 300);
    Node jqb = new Node("JQB", 400, 250);
    Node ictDepartment = new Node("ICT Department", 550, 300);

    // Add nodes to the graph
    graph.addNode(balmeLibrary);
    graph.addNode(centralCafeteria);
    graph.addNode(voltaHall);
    graph.addNode(commonwealthHall);
    graph.addNode(akuaffoHall);
    graph.addNode(nBlock);
    graph.addNode(jqb);
    graph.addNode(ictDepartment);

    // Define neighbors and distances
    balmeLibrary.addNeighbor(centralCafeteria, 5);
    balmeLibrary.addNeighbor(voltaHall, 10);
    centralCafeteria.addNeighbor(commonwealthHall, 7);
    voltaHall.addNeighbor(commonwealthHall, 3);
    balmeLibrary.addNeighbor(akuaffoHall, 15);
    akuaffoHall.addNeighbor(nBlock, 5);
    nBlock.addNeighbor(jqb, 10);
    jqb.addNeighbor(ictDepartment, 8);
    commonwealthHall.addNeighbor(ictDepartment, 12);

    // Run A* algorithm to find the shortest path from Balme Library to Commonwealth
    // Hall
    AStarAlgorithm aStar = new AStarAlgorithm();
    List<Node> path = aStar.findPath(graph, balmeLibrary, ictDepartment);

    // Visualize the graph and the shortest path
    MapVisualizer.visualize(graph, path);
  }
}
