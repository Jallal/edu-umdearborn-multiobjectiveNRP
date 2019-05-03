package thiagodnf.doupr.export.json.generator;

import java.util.ArrayList;
import java.util.List;

public class JSONRotatingClusterLayoutGenerator extends JSONAbstractGenerator {

	public JSONRotatingClusterLayoutGenerator(String exportFilename) {
		super(exportFilename);
	}

	public void print(Children root, int nivel, StringBuilder builder) {

		String separator = "";

		for (int i = 0; i < nivel; i++) {
			separator += "  ";
		}

		String s = String.format("{\"name\": \"%S\", \"size\": 3938,\"children\":}", root.name);

		builder.append(s);

		System.out.println(separator + root.name);

		for (Children c : root.children) {
			print(c, nivel + 1, builder);
		}
	}

	@Override
	public String getContent(Object parent, ProjectObject project) {

		Children root = new Children("Root");

		for (PackageObject p : project.getPackages()) {

			for (ClassObject cls : p.getClasses()) {

				root.addNode(cls.getName().split("\\."), 0);
			}
		}

		return root.toString().toLowerCase();
	}

	@Override
	protected String getSuffix() {
		return ".rcl.json";
	}

	@Override
	public String getContent(Object parent, ProjectObject project, ClassObject cls) {
		return null;
	}

	public class Children {

		public String name;

		public List<Children> children;

		public int size;

		public Children(String name) {
			this.name = name;
			this.children = new ArrayList<>();
		}

		public Children get(String name) {

			for (Children c : children) {
				if (c.name.equalsIgnoreCase(name)) {
					return c;
				}
			}

			return null;
		}

		public void addNode(String[] name, int index) {

			if (index == name.length) {
				return;
			}

			Children c = get(name[index]);

			if (c == null) {
				children.add(new Children(name[index]));
			}

			get(name[index]).addNode(name, index + 1);
		}

		public String toString() {
			if (children.isEmpty()) {
				return String.format("{\"name\": \"%S\", \"size\": 1}", name);
			}

			return String.format("{\"name\": \"%S\", \"children\":%S}", name, children);
		}
	}
}
