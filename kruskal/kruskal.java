public class Kruskal {

	public int kruskal(Graph graph) {

		int result = 0;

		// will sort the edges in ascending order based on weights;
		PriorityQueue<Edge> queue = new PriorityQueue<>((a, b) -> (a.weight - b.weight));
		for (Edge edge : graph.edges) {
			queue.add(edge);
		}

		// list of visited nodes, will be used to avoid cycles;
		List<Vertex> visited = new ArrayList<>();

		while (!queue.isEmpty()) {
			Edge temp = queue.poll();
			// check if the edge forms a cycle;
			if (!(visited.contains(temp.u)) && !(visited.contains(temp.v))) {
				visited.add(temp.u);
				visited.add(temp.v);

				// update the result with the weight;
				result += temp.weight;
			}
		}

		return result;
	}
}