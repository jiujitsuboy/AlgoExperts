package training.medium.graphs;

/**
 * Search the common parent between two vertex on the graph
 * 
 * @author JoseAlejandro
 *
 */
public class YoungestCommonAncestor {

	public static void main(String[] args) {

		GraphVertexInverted vertexA = new GraphVertexInverted("A", null);
		GraphVertexInverted vertexB = new GraphVertexInverted("B", vertexA);
		GraphVertexInverted vertexC = new GraphVertexInverted("C", vertexA);
		GraphVertexInverted vertexD = new GraphVertexInverted("D", vertexA);
		GraphVertexInverted vertexE = new GraphVertexInverted("E", vertexA);
		GraphVertexInverted vertexF = new GraphVertexInverted("F", vertexA);
		GraphVertexInverted vertexG = new GraphVertexInverted("G", vertexB);
		GraphVertexInverted vertexH = new GraphVertexInverted("H", vertexB);
		GraphVertexInverted vertexI = new GraphVertexInverted("I", vertexB);
		GraphVertexInverted vertexJ = new GraphVertexInverted("J", vertexC);
		GraphVertexInverted vertexK = new GraphVertexInverted("K", vertexD);
		GraphVertexInverted vertexL = new GraphVertexInverted("L", vertexD);
		GraphVertexInverted vertexM = new GraphVertexInverted("M", vertexF);
		GraphVertexInverted vertexN = new GraphVertexInverted("N", vertexF);
		GraphVertexInverted vertexO = new GraphVertexInverted("O", vertexH);
		GraphVertexInverted vertexP = new GraphVertexInverted("P", vertexH);
		GraphVertexInverted vertexQ = new GraphVertexInverted("Q", vertexH);
		GraphVertexInverted vertexR = new GraphVertexInverted("R", vertexH);
		GraphVertexInverted vertexS = new GraphVertexInverted("S", null);
		GraphVertexInverted vertexT = new GraphVertexInverted("T", vertexP);
		GraphVertexInverted vertexU = new GraphVertexInverted("U", vertexP);
		GraphVertexInverted vertexV = new GraphVertexInverted("V", vertexR);
		GraphVertexInverted vertexW = new GraphVertexInverted("W", vertexV);
		GraphVertexInverted vertexX = new GraphVertexInverted("X", vertexV);
		GraphVertexInverted vertexY = new GraphVertexInverted("Y", vertexV);
		GraphVertexInverted vertexZ = new GraphVertexInverted("Z", vertexX);

		System.out.println(getYoungestCommonAncestor(vertexZ, vertexT));
		System.out.println(getYoungestCommonAncestor(vertexL, vertexU));
		System.out.println(getYoungestCommonAncestor(vertexL, vertexS));

	}

	/**
	 * Complexity
	 * 
	 * Time O(D)=> the D of the graph 
	 * 
	 * Space O(1)=> no extra variable.
	 * 
	 * @param node1
	 * @param node2
	 * @return
	 */
	public static GraphVertexInverted getYoungestCommonAncestor(GraphVertexInverted node1, GraphVertexInverted node2) {

		GraphVertexInverted youngestCommonAncestor = null;

		if (node1 != null && node2 != null) {

			int depthNode1 = node1.getDepth();
			int depthNode2 = node2.getDepth();
			int depthDiff = 0;

			GraphVertexInverted minDepthNodeParent = null;
			GraphVertexInverted maxDepthNodeParent = null;

			if (depthNode1 < depthNode2) {
				minDepthNodeParent = node1.getParent();
				maxDepthNodeParent = node2.getParent();
				depthDiff = depthNode2 - depthNode1;
			} else {
				minDepthNodeParent = node2.getParent();
				maxDepthNodeParent = node1.getParent();
				depthDiff = depthNode1 - depthNode2;
			}

			/*
			 * taking the deepest node and going up through his parent, depthDiff times.
			 * this way, we reach a common level between both nodes
			 */
			while (depthDiff > 0 && maxDepthNodeParent != null) {
				maxDepthNodeParent = maxDepthNodeParent.getParent();
				depthDiff--;
			}

			// validate if the current parents node are not null so we can take its
			// respective parents
			if (minDepthNodeParent != null && maxDepthNodeParent != null) {
				minDepthNodeParent = minDepthNodeParent.getParent();
				maxDepthNodeParent = maxDepthNodeParent.getParent();

				// if both parents are the same, we have a common ancestor
				youngestCommonAncestor = minDepthNodeParent.equals(maxDepthNodeParent) ? minDepthNodeParent : null;
			}
		}
		return youngestCommonAncestor;
	}

	private static class GraphVertexInverted {
		private Object value;
		private GraphVertexInverted parent;

		public GraphVertexInverted(Object value, GraphVertexInverted parent) {
			this.value = value;
			this.parent = parent;
		}

		public Object getValue() {
			return value;
		}

		public GraphVertexInverted getParent() {
			return parent;
		}

		@Override
		public String toString() {
			return String.format("[value:%s]-[parent:%s]", value, (parent != null) ? parent.getValue() : null);
		}

		public int getDepth() {
			int depth = 0;
			GraphVertexInverted tempParent = null;

			if (parent != null) {
				tempParent = parent.getParent();
				depth++;
			}

			while (tempParent != null) {
				tempParent = tempParent.getParent();
				depth++;
			}

			return depth;
		}

	}

}
