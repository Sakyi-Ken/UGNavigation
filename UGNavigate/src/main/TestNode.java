package main;

import routeFinder.Node;

public class TestNode {
  public static void main(String[] args) {
    // Create a couple of nodes
    Node balmeLibrary = new Node("Balme Library", 0, 0);
    Node centralCafeteria = new Node("Central Cafeteria", 2, 3);

    // Add a neighbor
    balmeLibrary.addNeighbor(centralCafeteria, 5);

    // Output the node details
    System.out.println("Node: " + balmeLibrary.getName());
    System.out.println("Coordinates: (" + balmeLibrary.getX() + ", " + balmeLibrary.getY() + ")");
    System.out.println("Neighbors: ");
    balmeLibrary.getNeighbors().forEach((neighbor, distance) -> {
      System.out.println(" - " + neighbor.getName() + " at distance " + distance);
    });
  }
}
