package edu.umich.ISELab.export.json.generator;

import edu.umich.ISELab.core.util.ProjectObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONInteractiveForceLayoutGenerator extends JSONHierarchicalEdgeBundlingGenerator {

	public JSONInteractiveForceLayoutGenerator(String exportFilename) {
		super(exportFilename);
	}

	@Override
	public String getContent(Object parent, ProjectObject project) {

		StringBuilder builder = new StringBuilder();

		// First, create a list with all nodes

		List<String> nodes = new ArrayList<>();

		nodes.addAll(getNodes(project.getCallGraph().getCallsOut()));

		// Second, map each node to a hash map

		Map<String, List<String>> edges = map(nodes);

		// Third, map the access calls to edges


		// Fourth, map the method calls to edges

		mapFromCallToEdge(edges, project.getCallGraph().getCallsOut());

		Map<String, ElementObject> mapElement = ProjectObjectUtils.mapElement(project);
		Map<String, ClassObject> mapParent = ProjectObjectUtils.mapParent(project);

		// Fifth

		Map<String, String> group = new HashMap<>();

		for (String s : nodes) {
			group.put(s, mapParent.get(s).getSimpleName());
		}

		Map<String, Node> map = new HashMap<>();

		for (String s : nodes) {

			String name = mapElement.get(s).getName();
			String id = name + "_" + mapParent.get(s).getSimpleName();

			Node n = new Node(mapElement.get(s).getName(), group.get(s));

			for (String e : edges.get(s)) {
				n.depends.add(mapElement.get(e).getName() + "_" + mapParent.get(e).getSimpleName());
			}

			if (!map.containsKey(id)) {
				map.put(id, n);
			}
		}

		builder.append("{\"data\": {");

		for (String key : map.keySet()) {
			builder.append("\"").append(key).append("\":").append(map.get(key)).append(",");
		}
		builder.append("},\"errors\":[]}");

		return builder.toString();
	}

	@Override
	protected String getSuffix() {
		return ".rcl.json";
	}

	@Override
	public String getContent(Object parent, ProjectObject project, ClassObject cls) {
		return null;
	}

	public class Node {

		public String name;

		public String type;

		public List<String> depends;

		public List<String> dependedOnBy;

		public String docs;

		public Node(String name, String type) {
			this.name = name;
			this.type = type;
			this.dependedOnBy = new ArrayList<>();
			this.depends = new ArrayList<>();
		}

		public String toString() {

			StringBuilder builder = new StringBuilder();

			builder.append("{");
			builder.append("\"name\": \"").append(name).append("\",");
			builder.append("\"type\":\"").append("group0").append("\",");
			builder.append("\"depends\":").append(jsonify(depends)).append(",");
			builder.append("\"dependedOnBy\":").append(jsonify(dependedOnBy)).append(",");
			builder.append("\"docs\": \"\"");
			builder.append("}");

			return builder.toString();
		}
	}
}
