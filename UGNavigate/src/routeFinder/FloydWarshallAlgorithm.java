package routeFinder;

import java.util.ArrayList;
import java.util.List;

public class FloydWarshallAlgorithm {
  public int[][] findShortestPaths(Graph graph) {
    int numNodes = graph.getNodes().size();
    int[][] distances = new int[numNodes][numNodes];
    int[][] next = new int[numNodes][numNodes]; // Matrix to store the next nodes

    // Initialize distances and the next matrix
    for (int i = 0; i < numNodes; i++) {
      for (int j = 0; j < numNodes; j++) {
        if (i == j) {
          distances[i][j] = 0;
          next[i][j] = -1; // No need to move for same node
        } else {
          int distance = graph.getDistance(i, j);
          distances[i][j] = (distance != Integer.MAX_VALUE) ? distance : Integer.MAX_VALUE;
          next[i][j] = (distance != Integer.MAX_VALUE) ? j : -1; // Set the next node if reachable
        }
      }
    }

    // Floyd-Warshall algorithm: update distances to find shortest paths
    for (int k = 0; k < numNodes; k++) {
      for (int i = 0; i < numNodes; i++) {
        for (int j = 0; j < numNodes; j++) {
          if (distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE) {
            int newDistance = distances[i][k] + distances[k][j];
            if (newDistance < distances[i][j]) {
              distances[i][j] = newDistance;
              next[i][j] = next[i][k]; // Update the next node to follow the shorter path
            }
          }
        }
      }
    }

    return next;
  }

  public List<Node> reconstructPath(Graph graph, Node start, Node end, int[][] next) {
    int startIdx = graph.getNodes().indexOf(start);
    int endIdx = graph.getNodes().indexOf(end);

    if (next[startIdx][endIdx] == -1) {
      return new ArrayList<>(); // No path exists
    }

    List<Node> path = new ArrayList<>();
    path.add(graph.getNodes().get(startIdx));
    while (startIdx != endIdx) {
      startIdx = next[startIdx][endIdx];
      path.add(graph.getNodes().get(startIdx));
    }

    return path;
  }
}
