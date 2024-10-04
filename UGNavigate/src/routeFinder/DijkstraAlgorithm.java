package routeFinder;

import java.util.*;

public class DijkstraAlgorithm {
    private Map<Node, Node> predecessors; // Store predecessors for path reconstruction
    private Map<Node, Integer> distances; // Store shortest distances

    public Map<Node, Integer> findShortestPath(Graph graph, Node source) {
        distances = new HashMap<>();
        predecessors = new HashMap<>();
        PriorityQueue<NodeDistance> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(nd -> nd.distance));
        Set<Node> visited = new HashSet<>();

        // Initialize distances to all nodes as infinity, except the source node
        for (Node node : graph.getNodes()) {
            distances.put(node, Integer.MAX_VALUE);
            predecessors.put(node, null); // Initialize previous nodes as null
        }
        distances.put(source, 0);
        priorityQueue.add(new NodeDistance(source, 0));

        while (!priorityQueue.isEmpty()) {
            NodeDistance current = priorityQueue.poll();
            Node currentNode = current.node;

            if (visited.contains(currentNode)) {
                continue;
            }
            visited.add(currentNode);

            // Update distances to each neighbor of the current node
            for (Map.Entry<Node, Integer> neighborEntry : currentNode.getNeighbors().entrySet()) {
                Node neighbor = neighborEntry.getKey();
                int weight = neighborEntry.getValue();

                int newDist = distances.get(currentNode) + weight;
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    predecessors.put(neighbor, currentNode); // Track the predecessor for path reconstruction
                    priorityQueue.add(new NodeDistance(neighbor, newDist));
                }
            }
        }

        return distances; // Return the distances to all nodes from the source
    }

    public List<Node> getShortestPathTo(Node target) {
        List<Node> path = new ArrayList<>();
        for (Node node = target; node != null; node = predecessors.get(node)) {
            path.add(node);
        }
        Collections.reverse(path); // Reverse the path because we're backtracking from the target
        return path;
    }

    // A helper class to store nodes and their distances in the priority queue
    private class NodeDistance {
        Node node;
        int distance;

        NodeDistance(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }
}
