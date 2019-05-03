package edu.umich.ISELab.export.json.generator;

import edu.umich.ISELab.core.util.ProjectObjectUtils;
import edu.umich.ISELab.core.util.StringUtils;
import edu.umich.ISELab.export.util.FormatterUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements a JSON generator for Class Diagram Chart
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-08-12
 */
public class JSONClassDiagramGenerator extends JSONAbstractGenerator {

	public JSONClassDiagramGenerator(String exportFilename) {
		super(exportFilename);
	}

	protected String processAttributes(ClassObject cls) {

		List<String> lines = new ArrayList<>();

		String template = "{\"name\": \"@NAME@\", \"type\": \"@SIMPLETYPE@\", \"visibility\": \"@VISIBILITY@\" }";

		for (AttributeObject attr : cls.getAttributes()) {
			lines.add(FormatterUtils.format(template, attr));
		}

		return StringUtils.join(lines, ",");
	}

	protected String processParameters(MethodObject m) {

		List<String> lines = new ArrayList<>();

		String template = "{\"name\": \"\", \"type\": \"@SIMPLETYPE@\"}";

		for (ParameterObject p : m.getParameters()) {
			lines.add(FormatterUtils.format(template, p));
		}

		return StringUtils.join(lines, ",");
	}

	protected String processMethods(ClassObject cls) {

		List<String> lines = new ArrayList<>();

		String template = "{ \"name\": \"%s\", \"parameters\": [%s], \"visibility\": \"%s\" }";

		for (MethodObject m : cls.getMethods()) {

			String name = m.getName();
			String visibility = m.getVisibility().toString().toLowerCase();
			String parameters = processParameters(m);

			lines.add(String.format(template, name, parameters, visibility));
		}

		return StringUtils.join(lines, ",");
	}

	protected String processClass(int id, ClassObject cls) {

		StringBuilder builder = new StringBuilder();

		builder.append("{");
		builder.append("\"key\": ").append(id).append(",");
		builder.append("\"name\": \"").append(cls.getSimpleName()).append("\",");
		builder.append("\"properties\": [").append(processAttributes(cls)).append("],");
		builder.append("\"methods\": [").append(processMethods(cls)).append("]");
		builder.append("}");

		return builder.toString();
	}

	protected Map<String, Integer> getIds(ProjectObject project) {

		Map<String, Integer> ids = new HashMap<>();

		int counterIds = 1;

		for (PackageObject pack : project.getPackages()) {

			for (ClassObject cls : pack.getClasses()) {

				// We have to define an unique id for each class. This
				// information will be used to link two class through the
				// relationships.

				if (!ids.containsKey(cls.getName())) {
					ids.put(cls.getName(), counterIds++);
				}
			}
		}

		return ids;
	}

	@Override
	public String getContent(Object parent, ProjectObject project) {

		// Map each class to an unique number
		Map<String, Integer> ids = getIds(project);

		// ====================================================================
		// Process the nodes
		// ====================================================================

		List<String> nodedata = new ArrayList<>();

		for (PackageObject pack : project.getPackages()) {

			for (ClassObject cls : pack.getClasses()) {

				nodedata.add(processClass(ids.get(cls.getName()), cls));
			}
		}

		// ====================================================================
		// Process the edges
		// ====================================================================

		List<String> linkdata = new ArrayList<>();

		for (PackageObject pack : project.getPackages()) {

			for (ClassObject cls : pack.getClasses()) {

				int from = ids.get(cls.getName());

				String superClass = cls.getSuperClass();

				if (superClass != null) {
					int to = ids.get(superClass);
					linkdata.add("{ \"from\": " + from + ", \"to\": " + to + ", \"relationship\": \"generalization\" }");
				}

				for (String inter : cls.getInterfaces()) {
					int to = ids.get(inter);
					linkdata.add("{ \"from\": " + from + ", \"to\": " + to + ", \"relationship\": \"realization\" }");
				}

				for (AttributeObject attr : cls.getAttributes()) {
					if (ids.containsKey(attr.getType())) {
						int to = ids.get(attr.getType());
						linkdata.add("{ \"from\": " + to + ", \"to\": " + from + ", \"relationship\": \"aggregation\" }");
					}
				}
			}
		}

		StringBuilder builder = new StringBuilder();

		builder.append("var nodedata = [").append(StringUtils.join(nodedata, ",")).append("];");
		builder.append("var linkdata = [").append(StringUtils.join(linkdata, ",")).append("];");

		return builder.toString();
	}

	@Override
	protected String getSuffix() {
		return ".cd.json";
	}

	@Override
	public String getContent(Object parent, ProjectObject project, ClassObject cls) {

		// Map each class to an unique number
		Map<String, Integer> ids = getIds(project);

		List<String> nodedata = new ArrayList<>();

		nodedata.add(processClass(ids.get(cls.getName()), cls));

		// ====================================================================
		// Process the edges
		// ====================================================================

		List<String> linkdata = new ArrayList<>();


		int from = ids.get(cls.getName());

		String superClass = cls.getSuperClass();

		if (superClass != null) {

			int to = ids.get(superClass);

			ClassObject superCls = ProjectObjectUtils.findByType(project, superClass);

			linkdata.add("{ \"from\": " + from + ", \"to\": " + to + ", \"relationship\": \"generalization\" }");

			String process = processClass(ids.get(superCls.getName()), superCls);

			if (!nodedata.contains(process)) {
				nodedata.add(process);
			}
		}

		for (String inter : cls.getInterfaces()) {
			int to = ids.get(inter);

			ClassObject interCls = ProjectObjectUtils.findByType(project, inter);

			linkdata.add("{ \"from\": " + from + ", \"to\": " + to + ", \"relationship\": \"realization\" }");

			String process = processClass(ids.get(interCls.getName()), interCls);

			if (!nodedata.contains(process)) {
				nodedata.add(process);
			}
		}

		for (AttributeObject attr : cls.getAttributes()) {

			if (ids.containsKey(attr.getType())) {

				int to = ids.get(attr.getType());

				ClassObject attrCls = ProjectObjectUtils.findByType(project, attr.getType());

				linkdata.add("{ \"from\": " + to + ", \"to\": " + from + ", \"relationship\": \"aggregation\" }");

				String process = processClass(ids.get(attrCls.getName()), attrCls);

				if (!nodedata.contains(process)) {
					nodedata.add(process);
				}
			}
		}


		StringBuilder builder = new StringBuilder();

		builder.append("var nodedata = [").append(StringUtils.join(nodedata, ",")).append("];");
		builder.append("var linkdata = [").append(StringUtils.join(linkdata, ",")).append("];");

		return builder.toString();
	}
}
