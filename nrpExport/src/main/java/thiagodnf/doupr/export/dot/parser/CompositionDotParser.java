package thiagodnf.doupr.export.dot.parser;

import thiagodnf.doupr.export.ExportParser;

import java.util.List;

public class CompositionDotParser extends ExportParser {

    @Override
    public String parse(List<ClassObject> classes, ClassObject cls) {

        StringBuilder builder = new StringBuilder();

//		List<String> compositionClasses = getCompositionClasses(classes, cls);
//
//		// To create this connection it is necessary to have at least a
//		// realization
//		if (compositionClasses.isEmpty()) {
//			return builder.toString();
//		}
//
//		for (String c : compositionClasses) {
//			builder.append(cls.getSimpleName().replaceAll("\\$", "__"));
//			builder.append(" -> ");
//			builder.append(ClassObjectUtils.findByType(classes, c).getSimpleName().replaceAll("\\$", "__"));
//			builder.append("[arrowtail=diamond]");
//			builder.append("\n");
//		}

        return builder.toString();
    }

//	public List<String> getCompositionClasses(List<ClassObject> classes, ClassObject cls) {
//
//		List<String> classNames = BlockUtils.getClassNames(classes);
//
//		List<String> compositionClass = new ArrayList<>();
//
//		for (AttributeObject attribute : cls.getAttributes()) {
//			if(classNames.contains(attribute.getType())){
//				if(!compositionClass.contains(attribute.getType())) {
//					compositionClass.add(attribute.getType());
//				}
//			}
//		}
//
//		return compositionClass;
//	}

}
