package training.util.classes;

import java.util.List;

public class GraphVertex {
	private String value;
	List<GraphVertex> edges;

	public GraphVertex(String value, List<GraphVertex> edges) {
		this.value = value;
		this.edges = edges;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<GraphVertex> getEdges() {
		return edges;
	}

	public void setEdges(List<GraphVertex> edges) {
		this.edges = edges;
	}

	@Override
	public String toString() {
		return String.format("%s - %s childs", value, (edges != null) ? edges.size() : 0);
	}

}
