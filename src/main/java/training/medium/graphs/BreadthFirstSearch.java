package training.medium.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import training.util.classes.GraphVertex;


/**
 * Traverse all the node of the graph iterating over all the elements of the first level and them going down
 * to the next level until reaching the last one
 * 
 * @author JoseAlejandro
 *
 */
public class BreadthFirstSearch {

	public static void main(String[] args) {
		GraphVertex vertexI = new GraphVertex("I", null);
		GraphVertex vertexJ = new GraphVertex("J", null);
		GraphVertex vertexF = new GraphVertex("F", List.of(vertexI, vertexJ));

		GraphVertex vertexE = new GraphVertex("E", null);
		GraphVertex vertexB = new GraphVertex("B", List.of(vertexE, vertexF));

		GraphVertex vertexK = new GraphVertex("K", null);
		GraphVertex vertexG = new GraphVertex("G", List.of(vertexK));
		GraphVertex vertexH = new GraphVertex("H", null);
		GraphVertex vertexD = new GraphVertex("D", List.of(vertexG, vertexH));

		GraphVertex vertexC = new GraphVertex("C", null);

		GraphVertex vertexA = new GraphVertex("A", List.of(vertexB, vertexC, vertexD));

		System.out.println(findBreadthFirstSearch(vertexA));

	}

	/**
	 * Using a queue, every vertex visited, should store their respective children on the queue, so this guarantee
	 * that the next vertex processed is going to be the next one in the order level.
	 * 
	 * Complexity
	 * 
	 * Time O(V+E) => we need to iterate over every vertex and also iterate over all the edges of that vertex.
	 * 
	 * Space O(V) => we create a list containing every vertex value. Also the queue in the worst case (where all the vertex
	 * are children of one vertex) is going to be as long as V-1. 
	 * 
	 * @param vertex
	 * @return
	 */
	public static List<String> findBreadthFirstSearch(GraphVertex vertex) {

		List<String> breadthFirst = new ArrayList<String>();
		GraphVertex currentVertex = null;

		Queue<GraphVertex> queue = new LinkedList<GraphVertex>();
		queue.add(vertex);

		while (!queue.isEmpty()) {
			currentVertex = queue.poll();
			if (currentVertex.getEdges() != null) {
				for (GraphVertex child : currentVertex.getEdges()) {
					queue.add(child);
				}
			}
			breadthFirst.add(currentVertex.getValue());
		}
		return breadthFirst;
	}

}
