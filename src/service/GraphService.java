package service;

import java.util.*;

public class GraphService {
    private final Map<String, List<String>> adjacencyList = new HashMap<>();


    public void addConnection(String from, String to) {
        adjacencyList.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
        adjacencyList.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
    }

    public void bfs(String start) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(start);
        queue.offer(start);

        while (!queue.isEmpty()) {
            String region = queue.poll();
            System.out.println("Visited region: " + region);

            for (String neighbor : adjacencyList.getOrDefault(region, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
    }
}
