package UI;

import routeFinder.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainApp extends JFrame {
  private Graph graph;
  private JComboBox<String> startComboBox;
  private JComboBox<String> endComboBox;
  private JComboBox<String> algorithmComboBox;
  private JButton findRouteButton;
  private JPanel mapPanel;

  public MainApp() {
    setTitle("UG Navigate - Pathfinding Visualization");
    setSize(1000, 700);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());

    // Initialize graph and UI components
    initializeGraph();
    initializeUI();
  }

  private void initializeGraph() {
    graph = new Graph();

    Node balmeLibrary = new Node("Balme Library", 50, 50);
    Node centralCafeteria = new Node("Central Cafeteria", 200, 80);
    Node voltaHall = new Node("Volta Hall", 350, 50);
    Node commonwealthHall = new Node("Commonwealth Hall", 500, 150);
    Node akuafoHall = new Node("Akuafo Hall", 100, 250);
    Node nBlock = new Node("N-Block", 250, 300);
    Node jqb = new Node("JQB", 400, 250);
    Node ictDepartment = new Node("ICT Department", 550, 300);

    graph.addNode(balmeLibrary);
    graph.addNode(centralCafeteria);
    graph.addNode(voltaHall);
    graph.addNode(commonwealthHall);
    graph.addNode(akuafoHall);
    graph.addNode(nBlock);
    graph.addNode(jqb);
    graph.addNode(ictDepartment);

    balmeLibrary.addNeighbor(centralCafeteria, 5);
    balmeLibrary.addNeighbor(voltaHall, 10);
    centralCafeteria.addNeighbor(commonwealthHall, 7);
    voltaHall.addNeighbor(commonwealthHall, 3);
    balmeLibrary.addNeighbor(akuafoHall, 15);
    akuafoHall.addNeighbor(nBlock, 5);
    nBlock.addNeighbor(jqb, 10);
    jqb.addNeighbor(ictDepartment, 8);
    commonwealthHall.addNeighbor(ictDepartment, 12);
  }

  private void initializeUI() {
    JPanel controlPanel = new JPanel();
    controlPanel.setLayout(new GridLayout(3, 2, 10, 10));

    JLabel startLabel = new JLabel("Start Location:");
    startComboBox = new JComboBox<>(getNodeNames());
    controlPanel.add(startLabel);
    controlPanel.add(startComboBox);

    JLabel endLabel = new JLabel("End Location:");
    endComboBox = new JComboBox<>(getNodeNames());
    controlPanel.add(endLabel);
    controlPanel.add(endComboBox);

    JLabel algorithmLabel = new JLabel("Algorithm:");
    algorithmComboBox = new JComboBox<>(new String[] { "Dijkstra", "Floyd-Warshall", "A*" });
    controlPanel.add(algorithmLabel);
    controlPanel.add(algorithmComboBox);

    findRouteButton = new JButton("Find Route");
    findRouteButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        findAndVisualizeRoute();
      }
    });

    add(controlPanel, BorderLayout.NORTH);
    add(findRouteButton, BorderLayout.SOUTH);

    mapPanel = new JPanel();
    add(mapPanel, BorderLayout.CENTER);
  }

  private String[] getNodeNames() {
    return graph.getNodes().stream().map(Node::getName).toArray(String[]::new);
  }

  private void findAndVisualizeRoute() {
    String startName = (String) startComboBox.getSelectedItem();
    String endName = (String) endComboBox.getSelectedItem();
    String algorithm = (String) algorithmComboBox.getSelectedItem();

    Node startNode = findNodeByName(startName);
    Node endNode = findNodeByName(endName);

    List<Node> path = null;

    switch (algorithm) {
      case "Dijkstra":
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();
        dijkstra.findShortestPath(graph, startNode); // Find the shortest path using Dijkstra
        path = dijkstra.getShortestPathTo(endNode); // Get the path without passing predecessors
        break;
      case "Floyd-Warshall":
        FloydWarshallAlgorithm floydWarshall = new FloydWarshallAlgorithm();
        int[][] next = floydWarshall.findShortestPaths(graph); // Get the next node table
        path = floydWarshall.reconstructPath(graph, startNode, endNode, next); // Reconstruct the path
        break;
      case "A*":
        AStarAlgorithm aStar = new AStarAlgorithm();
        path = aStar.findPath(graph, startNode, endNode); // Get the path using A* algorithm
        break;
    }

    visualizePath(path);
  }

  private Node findNodeByName(String name) {
    return graph.getNodes().stream().filter(node -> node.getName().equals(name)).findFirst().orElse(null);
  }

  private void visualizePath(List<Node> path) {
    mapPanel.removeAll(); // Clear the previous visualization
    MapVisualizer visualizer = new MapVisualizer(graph);

    if (path != null && !path.isEmpty()) {
      visualizer.highlightPath(path); // Highlight the found path
    }

    mapPanel.add(visualizer);
    mapPanel.revalidate();
    mapPanel.repaint();
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      MainApp app = new MainApp();
      app.setVisible(true);
    });
  }
}
