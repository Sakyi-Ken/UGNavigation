package routeFinder;

import java.util.ArrayList;
import java.util.List;

public class Graph {
  private List<Node> nodes;

  // Constructor to initialize the graph
  public Graph() {
    this.nodes = new ArrayList<>();
  }

  // Method to add a node to the graph
  public void addNode(Node node) {
    nodes.add(node);
  }

  // Method to get all nodes in the graph
  public List<Node> getNodes() {
    return nodes;
  }

  // Method to get the distance between two nodes
  public int getDistance(int fromIndex, int toIndex) {
    Node fromNode = nodes.get(fromIndex);
    Node toNode = nodes.get(toIndex);
    return fromNode.getNeighbors().getOrDefault(toNode, Integer.MAX_VALUE);
  }
}
