package thiagodnf.doupr.export.json.generator;

import thiagodnf.doupr.core.util.ProjectObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JSONHierarchicalEdgeBundlingGenerator extends JSONAbstractGenerator {

	public JSONHierarchicalEdgeBundlingGenerator(String exportFilename) {
		super(exportFilename);
	}

	protected List<String> getNodes(Map<String, List<String>> map) {

		List<String> list = new ArrayList<>();

		for (String key : map.keySet()) {

			if (!list.contains(key)) {
				list.add(key);
			}

			for (String value : map.get(key)) {

				if (!list.contains(value)) {
					list.add(value);
				}
			}
		}

		return list;
	}

	protected Map<String, List<String>> map(List<String> items) {

		Map<String, List<String>> map = new HashMap<>();

		for (String item : items) {

			if (!map.containsKey(item)) {

				map.put(item, new ArrayList<>());
			}
		}

		return map;
	}

	protected void mapFromAccessToEdge(Map<String, List<String>> edges, Map<String, List<String>> accesses) {

		for (String key : accesses.keySet()) {

			for (String method : accesses.get(key)) {
				edges.get(method).add(key);
			}
		}
	}

	protected void mapFromCallToEdge(Map<String, List<String>> edges, Map<String, List<String>> calls) {

		for (String key : calls.keySet()) {

			for (String method : calls.get(key)) {
				edges.get(key).add(method);
			}
		}
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

		mapFromCallToEdge(edges, project.getCallGraph().getCallsOut());

		// Fifth, convert the JSON file

		int counter = 0;

		Map<String, ElementObject> mapElement = ProjectObjectUtils.mapElement(project);
		Map<String, ClassObject> mapParent = ProjectObjectUtils.mapParent(project);

		builder.append("[");

		for (String key : edges.keySet()) {

			ElementObject el = mapElement.get(key);
			ClassObject cls = mapParent.get(key);

			builder.append("{");
			builder.append("\"name\":\"").append(cls.getSimpleName() + "_" + el.getName()).append("\",");
			builder.append("\"size\":").append(1).append(",");

			List<String> list = edges.get(key).stream().map(e -> mapParent.get(e).getSimpleName() + "_" + mapElement.get(e).getName()).collect(Collectors.toList());

			builder.append("\"imports\":").append(jsonify(list));
			builder.append("}");

			if (counter + 1 != edges.size()) {
				builder.append(",");
				counter++;
			}
		}

		builder.append("]");

		return builder.toString();
	}

	@Override
	protected String getSuffix() {
		return ".heb.json";
	}

	@Override
	public String getContent(Object parent, ProjectObject project, ClassObject cls) {

		Map<String, ElementObject> mapElement = ProjectObjectUtils.mapElement(project);
		Map<String, ClassObject> mapParent = ProjectObjectUtils.mapParent(project);

		Map<String, List<String>> edges = new HashMap<>();

		for (MethodObject m : cls.getMethods()) {

			String sourceMethod = cls.getSimpleName() + "_" + m.getName();

			if (!edges.containsKey(sourceMethod)) {
				edges.put(sourceMethod, new ArrayList<>());
			}

			for (String to : project.getCallGraph().getCallsFrom(m.getIdentifier())) {

				ElementObject el = mapElement.get(to);
				ClassObject elCls = mapParent.get(to);

				String target = elCls.getSimpleName() + "_" + el.getName();

				if (!edges.containsKey(target)) {
					edges.put(target, new ArrayList<>());
				}

				edges.get(sourceMethod).add(target);
			}
		}

		for (AttributeObject attr : cls.getAttributes()) {

			String sourceMethod = cls.getSimpleName() + "_" + attr.getName();

			if (!edges.containsKey(sourceMethod)) {
				edges.put(sourceMethod, new ArrayList<>());
			}

			for (String from : project.getCallGraph().getCallsTo(attr.getIdentifier())) {

				ElementObject el = mapElement.get(from);
				ClassObject elCls = mapParent.get(from);

				String target = elCls.getSimpleName() + "_" + el.getName();

				if (!edges.containsKey(target)) {
					edges.put(target, new ArrayList<>());
				}

				edges.get(sourceMethod).add(target);
			}
		}

		StringBuilder builder = new StringBuilder();

		int counter = 0;

		builder.append("[");

		for (String key : edges.keySet()) {

			List<String> destiny = edges.get(key);

			List<String> s = jsonify(destiny);

			builder.append("{\"name\": \"" + key + "\", \"size\": 1, \"imports\": " + s + "}");

			if (counter + 1 != edges.size()) {
				builder.append(",");
			}
		}

		builder.append("]");

		return builder.toString();
	}
}
