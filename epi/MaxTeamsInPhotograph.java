package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MaxTeamsInPhotograph {

    public static int findLargestNumberTeams(List<GraphVertex> graph) {
        Deque<GraphVertex> topologicalOrder = topologicalSort(graph);


        return maxLength(topologicalOrder);
    }

    public static Deque topologicalSort(List<GraphVertex> graph) {
        Deque<GraphVertex> deque = new ArrayDeque<>();
        for (GraphVertex graphVertex : graph) {
            if (!graphVertex.visited)
                dfs(graphVertex, deque);
        }
        return deque;
    }

    public static int maxLength(Deque<GraphVertex> deque) {
        int max = 0;
        while (!deque.isEmpty()) {
            GraphVertex graphVertex = deque.pollLast();
            max = Math.max(max, graphVertex.maxDistance);

            for (GraphVertex c : graphVertex.edges) {
                c.maxDistance = Math.max(c.maxDistance, graphVertex.maxDistance + 1);
            }

        }
        return max;
    }

    public static void dfs(GraphVertex vertex, Deque<GraphVertex> queue) {
        vertex.visited = true;
        for (GraphVertex v : vertex.edges) {
            if (!v.visited)
                dfs(v, queue);
        }
        queue.add(vertex);
    }

    public static class GraphVertex {
        public List<GraphVertex> edges = new ArrayList<>();
        // Set maxDistance = 0 to indicate unvisited vertex.
        public int maxDistance = 1;
        public boolean visited = false;
    }

    @EpiUserType(ctorParams = {int.class, int.class})
    public static class Edge {
    public int from;
    public int to;

    public Edge(int from, int to) {
      this.from = from;
      this.to = to;
    }
  }

  @EpiTest(testDataFile = "max_teams_in_photograph.tsv")
  public static int findLargestNumberTeamsWrapper(TimedExecutor executor, int k,
                                                  List<Edge> edges)
      throws Exception {
    if (k <= 0) {
      throw new RuntimeException("Invalid k value");
    }
    List<GraphVertex> graph = new ArrayList<>();
    for (int i = 0; i < k; i++) {
      graph.add(new GraphVertex());
    }
    for (Edge e : edges) {
      if (e.from < 0 || e.from >= k || e.to < 0 || e.to >= k) {
        throw new RuntimeException("Invalid vertex index");
      }
      graph.get(e.from).edges.add(graph.get(e.to));
    }

    return executor.run(() -> findLargestNumberTeams(graph));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MaxTeamsInPhotograph.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
