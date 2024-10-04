package routeFinder;

import java.util.*;

public class AStarAlgorithm {
  public List<Node> findPath(Graph graph, Node start, Node goal) {
    PriorityQueue<NodeCost> openSet = new PriorityQueue<>(Comparator.comparingInt(nc -> nc.cost));
    Set<Node> closedSet = new HashSet<>();
    Map<Node, Node> cameFrom = new HashMap<>();
    Map<Node, Integer> gScore = new HashMap<>();
    Map<Node, Integer> fScore = new HashMap<>();

    // Initialize scores for each node
    for (Node node : graph.getNodes()) {
      gScore.put(node, Integer.MAX_VALUE);
      fScore.put(node, Integer.MAX_VALUE);
    }
    gScore.put(start, 0);
    fScore.put(start, heuristic(start, goal));
    openSet.add(new NodeCost(start, fScore.get(start)));

    while (!openSet.isEmpty()) {
      Node current = openSet.poll().node;
      if (current.equals(goal)) {
        return reconstructPath(cameFrom, current);
      }

      closedSet.add(current);

      // Explore neighbors
      for (Map.Entry<Node, Integer> neighborEntry : current.getNeighbors().entrySet()) {
        Node neighbor = neighborEntry.getKey();
        int tentativeGScore = gScore.get(current) + neighborEntry.getValue();

        if (closedSet.contains(neighbor)) {
          continue;
        }

        if (tentativeGScore < gScore.get(neighbor)) {
          cameFrom.put(neighbor, current);
          gScore.put(neighbor, tentativeGScore);
          fScore.put(neighbor, gScore.get(neighbor) + heuristic(neighbor, goal));
          if (openSet.stream().noneMatch(nc -> nc.node.equals(neighbor))) {
            openSet.add(new NodeCost(neighbor, fScore.get(neighbor)));
          }
        }
      }
    }

    return Collections.emptyList(); // Return an empty path if no path is found
  }

  // Heuristic function: use Euclidean distance for the heuristic
  private int heuristic(Node a, Node b) {
    return (int) Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
  }

  // Reconstruct the path from the start node to the goal node
  private List<Node> reconstructPath(Map<Node, Node> cameFrom, Node current) {
    List<Node> path = new ArrayList<>();
    while (current != null) {
      path.add(current);
      current = cameFrom.get(current);
    }
    Collections.reverse(path);
    return path;
  }

  // Helper class to represent a node and its associated cost
  private class NodeCost {
    Node node;
    int cost;

    NodeCost(Node node, int cost) {
      this.node = node;
      this.cost = cost;
    }
  }
}
