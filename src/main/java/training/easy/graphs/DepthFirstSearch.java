package training.easy.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import training.util.classes.GraphVertex;

/**
 * Traverse every edge of a Graph in order (So it explores the first edge all way down to the bottom before
 * traverse the second one and so forth)
 * @author JoseAlejandro
 *
 */
public class DepthFirstSearch {

	private static Stack<VertexSnapshot> vertexOnHold = new Stack<>();

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

		List<String> depthVertex = new ArrayList<String>();

		findDepthFirstRecursive(vertexA, depthVertex);
		System.out.println(depthVertex);

		System.out.println(findDepthFirstIterative(vertexA));

	}

	
	
	/**
	 * Time O(V+E) => we iterate over all the vertex and also have to loop for every edge that the vertex has.
	 * Space O(V) => we store all the vertex in an list. The frames in the stack are the longest branch, so there are
	 *  irrelevant because they are a unit of V.
	 * @param vertex
	 * @param depthVertex
	 */
	private static void findDepthFirstRecursive(GraphVertex vertex, List<String> depthVertex) {

		depthVertex.add(vertex.getValue());

		if (vertex.getEdges() == null || vertex.getEdges().isEmpty()) {
			return;
		}
		vertex.getEdges().stream().forEach(v -> findDepthFirstRecursive(v, depthVertex));

		return;
	}

	private static List<String> findDepthFirstIterative(GraphVertex vertex) {
		List<String> depthVertex = new ArrayList<>();
		boolean hasEdges, hasParent;

		VertexSnapshot snapShot = new VertexSnapshot(vertex);
		

		while (snapShot != null) {

			hasEdges = !(snapShot.getVertex().getEdges() == null || snapShot.getVertex().getEdges().isEmpty());
			hasParent = !vertexOnHold.isEmpty();
			
			if (snapShot.currentEdgeIndex == -1) {
				depthVertex.add(snapShot.getVertex().getValue());
			}

			if (hasEdges && snapShot.hasMoreEdgeIndex()) {				

				vertexOnHold.push(snapShot);
				snapShot = new VertexSnapshot(snapShot.getVertex().getEdges().get(snapShot.getNextEdgeIndex()));				

			} else if (hasParent) {
				snapShot = vertexOnHold.pop();
			} else {
				snapShot = null;
			}

		}

		return depthVertex;
	}

	// Time O(V+E)
	// Space O(V)
	private static List<String> findDepthFirstIterative2(GraphVertex vertex) {

		List<String> depthVertex = new ArrayList<>();
		VertexSnapshot snapShot = null;
		boolean noExistEdges;
		Optional<VertexSnapshot> tempSnapShot;

		while (vertex != null) {

			depthVertex.add(vertex.getValue());

			noExistEdges = vertex.getEdges() == null || vertex.getEdges().isEmpty();

			if (noExistEdges) {
				tempSnapShot = getNextVertexSnapshot();
				if (tempSnapShot.isPresent()) {
					snapShot = tempSnapShot.get();
				} else {
					vertex = null;
				}
			} else {
				snapShot = new VertexSnapshot(vertex);
			}

			if (snapShot != null) {
				
				GraphVertex snapShotVertex = snapShot.getVertex();

				if (snapShot.hasMoreEdgeIndex()) {					
					snapShot.getNextEdgeIndex();
					vertex = snapShotVertex.getEdges().get(snapShot.getCurrentEdgeIndex());
					vertexOnHold.push(snapShot);
				} else {
					tempSnapShot = getNextVertexSnapshot();
					if (tempSnapShot.isPresent()) {
						vertex = tempSnapShot.get().getVertex();
					} else {
						vertex = null;
					}
				}
			}

		}

		return depthVertex;
	}

	private static Optional<VertexSnapshot> getNextVertexSnapshot() {

		Optional<VertexSnapshot> vertex;

		if (vertexOnHold.isEmpty()) {
			vertex = Optional.empty();
		} else {
			vertex = Optional.of(vertexOnHold.pop());
		}

		return vertex;

	}

	/**
	 * Auxiliary Class to store state of a GraphVertex, before go into his edges
	 * 
	 * @author JoseAlejandro
	 *
	 */
	private static class VertexSnapshot {
		private GraphVertex vertex;
		private int currentEdgeIndex;

		public VertexSnapshot(GraphVertex vertex) {
			this.vertex = vertex;
			currentEdgeIndex = -1;
		}

		public GraphVertex getVertex() {
			return vertex;
		}

		public int getCurrentEdgeIndex() {
			return currentEdgeIndex;
		}

		public int getNextEdgeIndex() {
			return ++currentEdgeIndex;
		}

		public boolean hasMoreEdgeIndex() {
			return currentEdgeIndex < vertex.getEdges().size()-1;
		}

	}

}
