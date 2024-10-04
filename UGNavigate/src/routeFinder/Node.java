package routeFinder;

import java.util.HashMap;
import java.util.Map;

public class Node {
    private String name;
    private Map<Node, Integer> neighbors;
    private int x;
    private int y;

    // Constructor
    public Node(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.neighbors = new HashMap<>();
    }

    // Getter for the node's name
    public String getName() {
        return name;
    }

    // Getter for the neighbors
    public Map<Node, Integer> getNeighbors() {
        return neighbors;
    }

    // Method to add a neighbor
    public void addNeighbor(Node neighbor, int distance) {
        neighbors.put(neighbor, distance);
    }

    // Getters for coordinates
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
