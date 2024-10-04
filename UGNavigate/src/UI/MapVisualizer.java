package UI;

import routeFinder.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MapVisualizer extends JPanel {
  private Graph graph;
  private List<Node> highlightedPath;
  private double scale = 1.0; // For zooming

  public MapVisualizer(Graph graph) {
    this.graph = graph;
    this.highlightedPath = null;
    setPreferredSize(new Dimension(600, 400));
  }

  public void highlightPath(List<Node> path) {
    this.highlightedPath = path;
    repaint(); // Repaint to show the highlighted path
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    // Apply scaling for zoom
    g2d.scale(scale, scale);

    // Set anti-aliasing for smoother graphics
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    drawGraph(g2d);
    if (highlightedPath != null) {
      drawHighlightedPath(g2d);
    }
  }

  private void drawGraph(Graphics2D g) {
    for (Node node : graph.getNodes()) {
      int x = node.getX();
      int y = node.getY();

      g.setColor(new Color(50, 50, 50)); // Dark gray for nodes
      g.fillOval(x - 5, y - 5, 12, 12); // Larger node size
      g.setColor(Color.WHITE);
      g.drawString(node.getName(), x - 15, y - 10); // Position the name above the node

      g.setColor(new Color(100, 100, 100)); // Lighter gray for edges
      for (Node neighbor : node.getNeighbors().keySet()) {
        int nx = neighbor.getX();
        int ny = neighbor.getY();
        g.drawLine(x, y, nx, ny);
      }
    }
  }

  private void drawHighlightedPath(Graphics2D g) {
    g.setColor(new Color(255, 69, 0)); // Orange-Red color for the path
    g.setStroke(new BasicStroke(2)); // Thicker lines for the path
    for (int i = 0; i < highlightedPath.size() - 1; i++) {
      Node current = highlightedPath.get(i);
      Node next = highlightedPath.get(i + 1);
      g.drawLine(current.getX(), current.getY(), next.getX(), next.getY());
    }
  }

  // Zooming functionality
  public void setScale(double scale) {
    this.scale = scale;
    revalidate();
    repaint();
  }

  public static void visualize(Graph graph, List<Node> path) {
    JFrame frame = new JFrame("Graph Visualization");
    MapVisualizer panel = new MapVisualizer(graph);
    panel.highlightPath(path);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
