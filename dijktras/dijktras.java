public class Dijkstras {

    public HashMap<Vertex, Integer> dijkstras(Graph graph, Vertex start) {
    
    HashMap<Vertex, Integer> valueMap = new HashMap<>();

    // will sort in ascending order.
    PriorityQueue<Vertex> queue = new PriorityQueue<>((a, b) -> (a.distance - b.distance));

    for (Vertex v : graph.adjList.keySet()) {
        if (v.equals(start)) {
            valueMap.put(v, 0);
        } else {
            valueMap.put(v, Integer.MAX_VALUE); // put all others' distance as infinity
        }
    }

    queue.add(start);

    while (!queue.isEmpty()) {
        // get the vertex left with least distance;
        Vertex temp = queue.poll();
        // get its adjacent vertices;
        for (Vertex vertex : graph.adjList.get(temp) {
            // calculate distance;
            int distance = temp.distance + vertex.distance;
            // update if smaller
            if (distance < valueMap.get(vertex)) {
                valueMap.put(vertex, distance);
                // add vertex to the queue
                queue.add(vertex);
            }
        }
    }

    return valueMap;
}